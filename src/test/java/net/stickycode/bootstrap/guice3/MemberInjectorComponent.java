package net.stickycode.bootstrap.guice3;

import com.google.inject.MembersInjector;

import net.stickycode.stereotype.StickyComponent;

@StickyComponent
public class MemberInjectorComponent
    implements MembersInjector<String> {

  @Override
  public void injectMembers(String instance) {
  }

}
