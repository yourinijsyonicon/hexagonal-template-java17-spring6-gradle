package be.yonicon.template.config;

import be.yonicon.template.customer.*;
import be.yonicon.template.domain.DomainEventPublisher;
import be.yonicon.template.domain.customer.CustomerFactory;
import be.yonicon.template.domain.customer.CustomerRepository;
import be.yonicon.template.inmem.InMemoryCustomerRepository;
import be.yonicon.template.query.customer.CustomersArchive;
import be.yonicon.template.query.customer.GetCustomerQuery;
import be.yonicon.template.query.customer.ListCustomersQuery;
import be.yonicon.template.usecase.customer.create.CreateCustomerUseCase;
import be.yonicon.template.usecase.customer.delete.DeleteCustomerUseCase;
import be.yonicon.template.usecase.customer.update.UpdateCustomerUseCase;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    @ConditionalOnProperty(value = "be.yonicon.template.infra.customer.backend", havingValue = "inmem")
    InMemoryCustomerRepository inMemoryCustomerRepository() {
        return InMemorySetup.inMemoryCustomerRepository();
    }

    @Bean
    CreateCustomer createCustomer(CustomerRepository customerRepository,
                                  CustomerFactory customerFactory,
                                  DomainEventPublisher domainEventPublisher) {
        return new CreateCustomerUseCase(customerRepository, customerFactory, domainEventPublisher);
    }

    @Bean
    UpdateCustomer updateCustomer(CustomerRepository customerRepository,
                                  DomainEventPublisher domainEventPublisher) {
        return new UpdateCustomerUseCase(customerRepository, domainEventPublisher);
    }

    @Bean
    DeleteCustomer deleteCustomer(CustomerRepository customerRepository,
                                  DomainEventPublisher domainEventPublisher) {
        return new DeleteCustomerUseCase(customerRepository, domainEventPublisher);
    }

    @Bean
    ListCustomer listCustomer(CustomersArchive customersArchive) {
        return new ListCustomersQuery(customersArchive);
    }

    @Bean
    GetCustomer getCustomer(CustomersArchive customersArchive) {
        return new GetCustomerQuery(customersArchive);
    }

    @Bean
    CustomerFactory customerFactory(CustomerRepository customerRepository) {
        return new CustomerFactory(customerRepository);
    }
}
