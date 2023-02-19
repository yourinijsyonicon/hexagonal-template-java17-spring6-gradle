package be.yonicon.template.customer;

import be.yonicon.template.vocabulary.CustomerId;

public interface UpdateCustomerPresenter {
    void success();

    void customerIdDoesNotExist(CustomerId id);
}
