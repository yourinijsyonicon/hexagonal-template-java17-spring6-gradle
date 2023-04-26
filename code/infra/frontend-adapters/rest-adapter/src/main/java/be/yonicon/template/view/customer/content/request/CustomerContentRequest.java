package be.yonicon.template.view.customer.content.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


@Valid
public class CustomerContentRequest {
    @NotBlank(message = "Please provide The Commercial Name")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The Commercial Name of our Customer")
    private String commercialName;

    @NotBlank(message = "Please provide the Legal Name")
    @Schema(description = "The Legal Name of our Customer")
    private String legalName;

    @NotBlank(message = "Please provide the Contact Person")
    @Schema(description = "The Contact Person of our customer")
    private String contactPerson;

    @Length(min = 10, max = 10, message = "The Vat number needs to be 10 characters long")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The VAT number of our customer, format is without symbols (f.e. 0123456789)")
    private String vatNr;

    public CustomerContentRequest() {
    }

    public CustomerContentRequest(String commercialName, String legalName, String contactPerson, String vatNr) {
        this.commercialName = commercialName;
        this.legalName = legalName;
        this.contactPerson = contactPerson;
        this.vatNr = vatNr;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setVatNr(String vatNr) {
        this.vatNr = vatNr;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public String getLegalName() {
        return legalName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getVatNr() {
        return vatNr;
    }
}
