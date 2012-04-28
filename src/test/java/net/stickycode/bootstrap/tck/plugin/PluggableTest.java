package net.stickycode.bootstrap.tck.plugin;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import net.stickycode.bootstrap.tck.AbstractBootstrapTest;

public class PluggableTest
    extends AbstractBootstrapTest {

  @Inject
  Set<Pluggable> plugged;
  
  @Test
  public void verify() {
    assertThat(plugged).hasSize(2);
  }
}
