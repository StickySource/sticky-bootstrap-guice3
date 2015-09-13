package net.stickycode.bootstrap.guice3;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import net.stickycode.metadata.MetadataResolverRegistry;
import net.stickycode.stereotype.StickyComponent;

@StickyComponent
public class SomeTypeListener
    implements TypeListener {

  private Logger log = LoggerFactory.getLogger(getClass());

  @Inject
  MetadataResolverRegistry registry;

  @Override
  public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
    log.debug("{} with {}", type, registry);
  }

}
