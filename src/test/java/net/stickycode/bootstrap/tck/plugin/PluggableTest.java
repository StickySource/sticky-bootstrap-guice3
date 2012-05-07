package net.stickycode.bootstrap.tck.plugin;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Set;

import javax.inject.Inject;

import net.stickycode.bootstrap.tck.AbstractBootstrapTest;

import org.junit.Test;

public class PluggableTest
    extends AbstractBootstrapTest {

  @Inject
  Set<Pluggable> plugged;
  
  @Inject
  Set<GenericPluggable<?>> genericPlugged;
  
  @Inject
  Set<GenericPluggable> genericPluggedNoWildcard;
  
  @Test
  public void verify() {
    assertThat(plugged).hasSize(2);
    assertThat(genericPluggedNoWildcard).hasSize(3);
    assertThat(genericPlugged).hasSize(3);
  }
}
