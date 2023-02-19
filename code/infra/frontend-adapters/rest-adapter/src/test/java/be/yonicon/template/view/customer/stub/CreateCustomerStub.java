package be.yonicon.template.view.customer.stub;

import be.yonicon.template.customer.CreateCustomer;
import be.yonicon.template.customer.CreateCustomerPresenter;
import be.yonicon.template.vocabulary.CustomerDTO;

public class CreateCustomerStub implements CreateCustomer {
    private CustomerDTO customerDTO;

    @Override
    public void create(CustomerDTO dto, CreateCustomerPresenter presenter) {
        this.customerDTO = dto;
    }

    public CustomerDTO getCustomerDto() {
        return customerDTO;
    }
}
