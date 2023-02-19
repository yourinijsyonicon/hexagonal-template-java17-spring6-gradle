package be.yonicon.template.customer;

import be.yonicon.template.vocabulary.CustomerDTO;

public interface CreateCustomer {
    void create(CustomerDTO dto, CreateCustomerPresenter presenter);
}
