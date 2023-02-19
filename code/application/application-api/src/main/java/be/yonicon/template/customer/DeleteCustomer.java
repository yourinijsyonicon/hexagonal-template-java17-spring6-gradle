package be.yonicon.template.customer;

import be.yonicon.template.vocabulary.CustomerId;

public interface DeleteCustomer {
    void delete(CustomerId id, DeleteCustomerPresenter presenter);
}
