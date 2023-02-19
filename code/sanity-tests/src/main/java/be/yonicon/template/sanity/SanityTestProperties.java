package be.yonicon.template.sanity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

public class SanityTestProperties {
    private static final SanityTestProperties SANITY_TEST_PROPERTIES = new SanityTestProperties();

    private final Logger LOGGER = LoggerFactory.getLogger(SanityTestProperties.class);
    private final Properties properties;

    private SanityTestProperties() {
        var config = System.getProperty("config");
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource;
        String location;
        if (config != null && !config.isEmpty()) {
            location = String.format("file:%s", config);
        } else {
            location = String.format("classpath:%s", "sanity-tests.yml");
        }

        LOGGER.info("Loading properties from: {}", location);
        resource = resourceLoader.getResource(location);

        properties = new Properties();
        try {
            properties.load(resource.getInputStream());
        } catch (IOException e) {
            throw new CouldNotLoadPropertiesException("Could not load properties.", e);
        }
    }

    public static SanityTestProperties singleton() {
        return SANITY_TEST_PROPERTIES;
    }

    public String baseUrl() {
        return getProperty("base-url");
    }

    private String getProperty(String key) {
        return properties.getProperty(key);
    }

    static class CouldNotLoadPropertiesException extends RuntimeException {
        public CouldNotLoadPropertiesException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}