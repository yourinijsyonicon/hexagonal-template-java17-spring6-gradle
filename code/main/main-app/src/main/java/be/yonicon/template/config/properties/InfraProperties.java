package be.yonicon.template.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import static java.util.Optional.ofNullable;

@ConfigurationProperties("be.yonicon.template.infra")
public class InfraProperties {
    @NestedConfigurationProperty
    private SecurityProperties security = new SecurityProperties();

    @NestedConfigurationProperty
    private CustomerProperties customer = new CustomerProperties();

    public SecurityProperties getSecurity() {
        return security;
    }

    public CustomerProperties getCustomer() {
        return customer;
    }

    public void setSecurity(final SecurityProperties security) {
        this.security = security;
    }

    public void setCustomer(final CustomerProperties customer) {
        this.customer = customer;
    }

    public boolean isSecurityEnabled() {
        return ofNullable(security)
                .map(SecurityProperties::isEnabled)
                .orElse(false);
    }

    public boolean isCustomerInMemorySet() {
        return ofNullable(customer)
                .map(CustomerProperties::getBackend)
                .map("inmem"::equals)
                .orElse(false);
    }
}
