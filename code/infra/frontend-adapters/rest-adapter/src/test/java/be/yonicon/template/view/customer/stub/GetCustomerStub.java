package be.yonicon.template.view.customer.stub;

import be.yonicon.template.customer.GetCustomer;
import be.yonicon.template.vocabulary.CustomerId;
import be.yonicon.template.vocabulary.CustomerItem;

import static java.util.Optional.ofNullable;

public class GetCustomerStub implements GetCustomer {
    private final CustomerItem stubbedItem;

    public GetCustomerStub(CustomerItem stubbedItem) {
        this.stubbedItem = stubbedItem;
    }

    @Override
    public CustomerItem getCustomer(CustomerId id) {
        return ofNullable(stubbedItem)
                .filter(i -> i.getId().equals(id))
                .orElse(null);
    }
}
