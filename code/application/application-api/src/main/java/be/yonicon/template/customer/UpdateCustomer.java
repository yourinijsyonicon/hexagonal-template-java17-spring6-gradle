package be.yonicon.template.customer;

import be.yonicon.template.vocabulary.CustomerDTO;
import be.yonicon.template.vocabulary.CustomerId;

public interface UpdateCustomer {
    void update(CustomerId id, CustomerDTO dto, UpdateCustomerPresenter presenter);
}
