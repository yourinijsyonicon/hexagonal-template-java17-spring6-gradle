package be.yonicon.template.sanity;

import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class SanityTestApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SanityTestApplication.class);

    public static void main(String[] args) {

        if (args.length > 0) {
            System.setProperty("config", args[0]);
        }
        var baseUrl = SanityTestProperties.singleton().baseUrl();
        LOGGER.info("Url = {}", baseUrl);

        var app = new SanityTestApplication();
        app.run();

    }

    private void run() {
        LOGGER.info("Running sanity tests for the magda platform...");
        var request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectPackage(this.getClass().getPackageName()))
                .filters(includeClassNamePatterns(".*Test"))
                .build();
        var launcher = LauncherFactory.create();

        launcher.discover(request);
        launcher.registerTestExecutionListeners(new LoggingTestExecutionListener());
        launcher.execute(request);
    }
}
