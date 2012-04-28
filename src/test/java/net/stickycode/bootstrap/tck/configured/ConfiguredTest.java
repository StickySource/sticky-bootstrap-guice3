package net.stickycode.bootstrap.tck.configured;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.Test;

import net.stickycode.bootstrap.tck.AbstractBootstrapTest;

public class ConfiguredTest
    extends AbstractBootstrapTest {

  @Inject
  AConfiguredTuple tuple;

  @Inject
  AConfiguredTuple tuple2;

  @Inject
  ConfguredTuple tupleContract;

  @Test
  public void verify() {
    assertThat(tuple).isNotSameAs(tupleContract);
    assertThat(tuple).isNotSameAs(tuple2);
  }
}
