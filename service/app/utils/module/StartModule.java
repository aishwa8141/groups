package utils.module;

import com.google.inject.AbstractModule;

/** This class is responsible for creating instance of ApplicationStart at server startup time. */
public class StartModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(ApplicationStart.class).asEagerSingleton();
  }
}
