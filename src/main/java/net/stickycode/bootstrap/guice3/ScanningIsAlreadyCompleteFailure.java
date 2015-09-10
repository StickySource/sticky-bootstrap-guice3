package net.stickycode.bootstrap.guice3;

import net.stickycode.stereotype.failure.ParameterisedFailure;

@SuppressWarnings("serial")
public class ScanningIsAlreadyCompleteFailure
    extends ParameterisedFailure {

  public ScanningIsAlreadyCompleteFailure() {
    super("Tried to add more packages to scan after the bootstrap was already complete");
  }

}
