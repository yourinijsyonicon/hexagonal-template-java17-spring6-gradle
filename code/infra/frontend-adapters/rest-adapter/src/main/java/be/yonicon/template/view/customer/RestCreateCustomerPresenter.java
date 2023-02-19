package be.yonicon.template.view.customer;

import be.yonicon.template.customer.CreateCustomerPresenter;
import be.yonicon.template.view.ResourceConflictException;

public class RestCreateCustomerPresenter implements CreateCustomerPresenter {
    @Override
    public void success() {
        // nothing to do here...
    }

    @Override
    public void customerVatNrAlreadyExists(String vatNr) {
        throw new ResourceConflictException("Customer", "Vat Nr", vatNr);
    }
}
