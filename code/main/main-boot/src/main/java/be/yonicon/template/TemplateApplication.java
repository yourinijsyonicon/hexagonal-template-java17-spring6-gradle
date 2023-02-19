package be.yonicon.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ConfigurationPropertiesScan("be.yonicon.template")
public class TemplateApplication {
    public static void main(String[] args) {
        final var application = new SpringApplication(TemplateApplication.class);
        application.run(args);
    }
}
