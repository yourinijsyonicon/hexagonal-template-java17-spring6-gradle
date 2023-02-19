package be.yonicon.template.customer;

public interface CreateCustomerPresenter {
    void success();

    void customerVatNrAlreadyExists(String vatNr);
}
