package be.yonicon.template.sanity.ping;

import be.yonicon.template.sanity.SanityTestProperties;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"java:S5960", "java:S2139"})
class PingSanityTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PingSanityTest.class);
    private static final String ENDPOINT = String.format("%s/ping", SanityTestProperties.singleton().baseUrl());

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void verifyPing() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(ENDPOINT, String.class);

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isEqualTo("i am alive");
        } catch (Exception e) {
            LOGGER.error("Error during POST for endpoint " + SanityTestProperties.singleton().baseUrl() , e);
            throw e;
        }
    }
}
