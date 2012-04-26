package net.stickycode.bootstrap.tck;

import java.util.Set;

import javax.inject.Inject;

import net.stickycode.bootstrap.tck.plugin.Pluggable;
import net.stickycode.stereotype.StickyComponent;

@StickyComponent
public class Depender {

  @Inject
  Dependent dependent;
  
  @Inject
  Set<Pluggable> pluggables;
  
  @Inject
  SuperInterface superInterface;
  
  @Inject
  SubInterface subInterface;
  
  @Inject
  Hydra hydra;
  
  @Inject
  SixUp sixUp;
  
  @Inject
  SomethingProvider provider;
}
