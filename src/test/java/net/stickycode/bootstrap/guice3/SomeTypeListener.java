package net.stickycode.bootstrap.guice3;

import static org.assertj.core.api.StrictAssertions.assertThat;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import net.stickycode.metadata.MetadataResolverRegistry;
import net.stickycode.stereotype.StickyComponent;
import net.stickycode.stereotype.StickyFramework;

@StickyComponent
@StickyFramework
public class SomeTypeListener
    implements TypeListener {

  private Logger log = LoggerFactory.getLogger(getClass());

  @Inject
  MetadataResolverRegistry registry;

  @Override
  public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
    if (type.getRawType().isAnnotationPresent(StickyFramework.class))
      return;

    if (type.getRawType().getName().startsWith("com.google.inject"))
      return;

    assertThat(registry)
        .as("Type listeners should have metadata resolvers and other framework things injected in the application injector")
        .isNotNull();
  }

}
