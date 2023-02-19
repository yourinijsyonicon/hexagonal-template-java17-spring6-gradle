package be.yonicon.template.view.customer;

import be.yonicon.template.customer.DeleteCustomerPresenter;
import be.yonicon.template.view.ResourceNotFoundException;
import be.yonicon.template.vocabulary.CustomerId;

import static java.util.Optional.ofNullable;

public class RestDeleteCustomerPresenter implements DeleteCustomerPresenter {
    @Override
    public void success() {
        // nothing to do here...
    }

    @Override
    public void customerIdDoesNotExist(CustomerId id) {
        throw new ResourceNotFoundException(
                "Customer",
                ofNullable(id).map(CustomerId::toString).orElse("[No ID]"));
    }
}
