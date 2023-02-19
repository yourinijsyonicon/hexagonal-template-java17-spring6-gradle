package be.yonicon.template.view.customer.stub;

import be.yonicon.template.customer.UpdateCustomer;
import be.yonicon.template.customer.UpdateCustomerPresenter;
import be.yonicon.template.vocabulary.CustomerDTO;
import be.yonicon.template.vocabulary.CustomerId;

public class UpdateCustomerStub implements UpdateCustomer {
    private CustomerDTO customerDTO;
    private CustomerId customerId;

    @Override
    public void update(CustomerId id, CustomerDTO dto, UpdateCustomerPresenter presenter) {
        this.customerDTO = dto;
        this.customerId = id;
    }

    public CustomerDTO getCustomerDto() {
        return customerDTO;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }
}