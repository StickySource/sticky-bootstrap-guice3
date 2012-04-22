package net.stickycode.bootstrap.tck;

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
  }

}
