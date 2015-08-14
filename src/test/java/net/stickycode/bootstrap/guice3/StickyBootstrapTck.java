package net.stickycode.bootstrap.guice3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.google.inject.Provider;

import net.stickycode.bootstrap.tck.component.ComponentTest;
import net.stickycode.bootstrap.tck.component.ContractComponentTest;
import net.stickycode.bootstrap.tck.configured.ConfiguredTest;
import net.stickycode.bootstrap.tck.plugin.PluggableTest;

@SuiteClasses({
    ComponentTest.class,
    ContractComponentTest.class,
    ConfiguredTest.class,
    PluggableTest.class,
    Provider.class
})
@RunWith(Suite.class)
public class StickyBootstrapTck {

}
