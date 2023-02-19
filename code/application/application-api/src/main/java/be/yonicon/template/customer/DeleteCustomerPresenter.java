package be.yonicon.template.customer;

import be.yonicon.template.vocabulary.CustomerId;

public interface DeleteCustomerPresenter {
    void success();

    void customerIdDoesNotExist(CustomerId id);
}
