package be.yonicon.template.config;

import be.yonicon.template.domain.DomainEventPublisher;
import be.yonicon.template.inmem.InMemoryDomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainEventConfig {
    @Bean
    DomainEventPublisher domainEventPublisher() {
        return new InMemoryDomainEventPublisher();
    }
}
