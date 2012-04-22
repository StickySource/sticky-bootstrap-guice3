package net.stickycode.bootstrap.tck;

import javax.inject.Provider;

import net.stickycode.stereotype.StickyComponent;

@StickyComponent
public class SomethingProvider
    implements Provider<Something> {

  @Override
  public Something get() {
    return new Something();
  }

}
