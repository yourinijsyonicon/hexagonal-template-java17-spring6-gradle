package be.yonicon.template.view.customer.stub;

import be.yonicon.template.customer.ListCustomer;
import be.yonicon.template.vocabulary.CustomerItem;

import java.util.ArrayList;
import java.util.List;

public class ListCustomerStub implements ListCustomer {
    private final List<CustomerItem> stubbedItems;

    public ListCustomerStub(List<CustomerItem> stubbedItems) {
        this.stubbedItems = stubbedItems;
    }

    @Override
    public List<CustomerItem> getAllCustomers() {
        return new ArrayList<>(stubbedItems);
    }
}
