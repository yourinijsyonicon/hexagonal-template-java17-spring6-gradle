package be.yonicon.template.sanity;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTestExecutionListener implements TestExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingTestExecutionListener.class);

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.isTest()) {
            LOGGER.info("Started {} - {}", extractParentId(testIdentifier), testIdentifier.getDisplayName());
        }
    }

    @Override
    public void executionSkipped(TestIdentifier testIdentifier, String reason) {
        LOGGER.info("Skipped {} - {}: {}", extractParentId(testIdentifier), testIdentifier.getDisplayName(), reason);
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            LOGGER.info("Finished {} - {}: {}", extractParentId(testIdentifier), testIdentifier.getDisplayName(),
                    testExecutionResult.getStatus());
        }
    }

    private static String extractParentId(final TestIdentifier testIdentifier) {
        return testIdentifier.getParentId().orElse("Unknown Parent Id");
    }


}
