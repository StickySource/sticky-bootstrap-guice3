/**
 * Copyright (c) 2011 RedEngine Ltd, http://www.RedEngine.co.nz. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package net.stickycode.bootstrap.guice3;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.stickycode.metadata.MetadataResolverRegistry;
import net.stickycode.stereotype.StickyComponent;
import net.stickycode.stereotype.StickyFramework;
import net.stickycode.stereotype.component.StickyRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.MembersInjector;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeListener;

import de.devsurf.injection.guice.install.InstallationContext.BindingStage;
import de.devsurf.injection.guice.scanner.features.BindingScannerFeature;

@Singleton
public class StickyStereotypeScannerFeature
    extends BindingScannerFeature {

  private Logger log = LoggerFactory.getLogger(getClass());

  @Inject
  MetadataResolverRegistry metadataResolver;

  @Override
  public BindingStage accept(Class<Object> annotatedClass, Map<String, Annotation> annotations) {
    if (isFrameworkComponent(annotatedClass))
      return BindingStage.IGNORE;

    if (metadataResolver.is(annotatedClass).metaAnnotatedWith(getComponentAnnotation()))
      return deriveStage(annotatedClass);

    return BindingStage.IGNORE;
  }

  protected boolean isFrameworkComponent(Class<Object> annotatedClass) {
    return metadataResolver.is(annotatedClass).metaAnnotatedWith(StickyFramework.class);
    // if (annotatedClass.isAnnotationPresent(StickyFramework.class))
    // return true;
    //
    // for (Class<?> type = annotatedClass; type != null; type = type.getSuperclass())
    // for (Class<?> contract : type.getInterfaces()) {
    // if (contract.isAnnotationPresent(StickyFramework.class))
    // return true;
    //
    // if (contract.isAssignableFrom(MembersInjector.class))
    // return true;
    //
    // if (contract.isAssignableFrom(TypeListener.class))
    // return true;
    //
    // if (contract.isAssignableFrom(InjectionListener.class))
    // return true;
    // }
    //
    // return false;
  }

  protected Class<? extends Annotation> getComponentAnnotation() {
    return StickyComponent.class;
  }

  protected BindingStage deriveStage(Class<Object> annotatedClass) {
    BindingStage calculateStage = calculateStage(annotatedClass);
    log.debug("adding {} at {}", annotatedClass.getName(), calculateStage);
    return calculateStage;
  }

  private BindingStage calculateStage(Class<Object> annotatedClass) {
    for (Class<?> contract : annotatedClass.getInterfaces()) {
      if (contract.isAnnotationPresent(StickyRepository.class))
        return BindingStage.BOOT_BEFORE;
    }

    return BindingStage.BOOT;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void process(Class<Object> annotatedClass, Map<String, Annotation> annotations) {
    List<Class<?>> interfaces = new ArrayList<Class<?>>();
    processInterface(annotatedClass, interfaces);
    
    for (Class<?> parent = annotatedClass.getSuperclass(); parent != null; parent = parent.getSuperclass()) {
      processInterface(annotatedClass, interfaces);
    }

    bind(annotatedClass, null, Scopes.SINGLETON);

    for (Class<?> interf : interfaces) {
      if (interf.isAssignableFrom(TypeListener.class))
        bindListener(annotatedClass);
      else
        if (!interf.isAssignableFrom(MembersInjector.class))
          bind(annotatedClass, (Class<Object>) interf, (Annotation) null, Scopes.SINGLETON);
    }
  }

  private void processInterface(Class<?> target, List<Class<?>> interfaces) {
    for (Class<?> class1 : target.getInterfaces()) {
      interfaces.add(class1);
      processInterface(class1, interfaces);
    }
  }

  private void bindListener(Class<Object> annotatedClass) {
    TypeListener typeListener = typeListener(annotatedClass);
    _binder.requestInjection(typeListener);
    _binder.bindListener(Matchers.any(), typeListener);
  }

  private TypeListener typeListener(Class<Object> annotatedClass) {
    try {
      return (TypeListener) annotatedClass.newInstance();
    }
    catch (InstantiationException e) {
      throw new RuntimeException(e);
    }
    catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}
