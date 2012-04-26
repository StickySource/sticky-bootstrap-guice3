package net.stickycode.bootstrap.tck;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import net.stickycode.bootstrap.guice3.StickyGuice;

import org.junit.Test;

import com.google.inject.Injector;

public class BootstrapTest {
  
  @Inject
  Depender depender;

  @Test
  public void verify() {
    Injector injector = StickyGuice.createApplicationInjector(getClass().getPackage().getName());
    injector.injectMembers(this);
    
    assertThat(depender.subInterface).isSameAs(depender.superInterface);
    
    // even though the provider has not scope its the same at each injection point
    // seems counter intuitive, I would expect a provider to be singleton scoped
    // in order to be the same at all injection points
    assertThat(depender.provider.get()).isNotSameAs(depender.provider.get());
    assertThat(depender.provider).isSameAs(depender.provider);
  }

}
