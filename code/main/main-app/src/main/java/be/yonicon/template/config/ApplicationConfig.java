package be.yonicon.template.config;

import be.yonicon.template.config.properties.InfraProperties;
import be.yonicon.template.customer.CreateCustomer;
import be.yonicon.template.customer.DeleteCustomer;
import be.yonicon.template.customer.GetCustomer;
import be.yonicon.template.customer.ListCustomer;
import be.yonicon.template.customer.UpdateCustomer;
import be.yonicon.template.domain.DomainEventPublisher;
import be.yonicon.template.domain.customer.CustomerFactory;
import be.yonicon.template.domain.customer.CustomerRepository;
import be.yonicon.template.query.customer.CustomersArchive;
import be.yonicon.template.query.customer.GetCustomerQuery;
import be.yonicon.template.query.customer.ListCustomersQuery;
import be.yonicon.template.usecase.customer.create.CreateCustomerUseCase;
import be.yonicon.template.usecase.customer.delete.DeleteCustomerUseCase;
import be.yonicon.template.usecase.customer.update.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    CustomerRepository customerRepository(InfraProperties properties) {
        if (properties.isCustomerInMemorySet()) {
            return InMemorySetup.inMemoryCustomerRepository();
        }

        throw new IllegalStateException("Customer Repository is required.");
    }

    @Bean
    CustomersArchive customersArchive(InfraProperties properties) {
        if (properties.isCustomerInMemorySet()) {
            return InMemorySetup.inMemoryCustomerRepository();
        }

        throw new IllegalStateException("Customers Archive is required.");
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
