package be.yonicon.template.view.customer.stub;

import be.yonicon.template.customer.DeleteCustomer;
import be.yonicon.template.customer.DeleteCustomerPresenter;
import be.yonicon.template.vocabulary.CustomerId;

public class DeleteCustomerStub implements DeleteCustomer {
    private CustomerId customerId;

    @Override
    public void delete(CustomerId id, DeleteCustomerPresenter presenter) {
        this.customerId = id;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }
}