package be.yonicon.template.view.customer;

import be.yonicon.template.view.ResourceNotFoundException;
import be.yonicon.template.view.customer.content.request.CustomerContentRequest;
import be.yonicon.template.view.customer.content.response.CustomerContentResponse;
import be.yonicon.template.view.customer.stub.CreateCustomerStub;
import be.yonicon.template.view.customer.stub.DeleteCustomerStub;
import be.yonicon.template.view.customer.stub.GetCustomerStub;
import be.yonicon.template.view.customer.stub.ListCustomerStub;
import be.yonicon.template.view.customer.stub.UpdateCustomerStub;
import be.yonicon.template.vocabulary.CustomerDTO;
import be.yonicon.template.vocabulary.CustomerId;
import be.yonicon.template.vocabulary.CustomerItem;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomerControllerTest {
    private static final CustomerId CUSTOMER_ID = CustomerId.newId();
    private static final CustomerDTO CUSTOMER_DTO = CustomerDTO.newBuilder()
            .withContactPerson("Contact Person")
            .withVatNr("Vat Nr")
            .withLegalName("Legal Name")
            .withCommercialName("Commercial Name")
            .build();
    public static final CustomerItem CUSTOMER_ITEM = CustomerItem.newBuilder()
            .withId(CUSTOMER_ID)
            .withDto(CUSTOMER_DTO)
            .build();
    private final GetCustomerStub getCustomer = new GetCustomerStub(CUSTOMER_ITEM);
    private final ListCustomerStub listCustomer = new ListCustomerStub(List.of(CUSTOMER_ITEM));
    private final CreateCustomerStub createCustomer = new CreateCustomerStub();
    private final UpdateCustomerStub updateCustomer = new UpdateCustomerStub();
    private final DeleteCustomerStub deleteCustomer = new DeleteCustomerStub();

    private final CustomerController controller = new CustomerController(
            createCustomer,
            deleteCustomer,
            updateCustomer,
            listCustomer,
            getCustomer);

    @Nested
    class GetCustomer {
        @Test
        void testGettingExistingCustomer() {
            CustomerContentResponse customer = controller.getCustomer(CUSTOMER_ID.toString());

            assertThat(customer.getId()).isEqualTo(CUSTOMER_ID.toString());
            assertThat(customer.getLegalName()).isEqualTo(CUSTOMER_DTO.getLegalName());
            assertThat(customer.getCommercialName()).isEqualTo(CUSTOMER_DTO.getCommercialName());
            assertThat(customer.getContactPerson()).isEqualTo(CUSTOMER_DTO.getContactPerson());
            assertThat(customer.getVatNr()).isEqualTo(CUSTOMER_DTO.getVatNr());
        }

        @Test
        void testGettingNonExistantCustomer() {
            assertThatThrownBy(() -> controller.getCustomer(CustomerId.newId().toString()))
                    .isInstanceOf(ResourceNotFoundException.class);
        }
    }

    @Nested
    class ListCustomers {
        @Test
        void testGettingExistingCustomer() {
            List<CustomerContentResponse> customers = controller.getCustomers();

            assertThat(customers)
                    .isNotEmpty()
                    .allSatisfy(customer -> {
                assertThat(customer.getId()).isEqualTo(CUSTOMER_ID.toString());
                assertThat(customer.getLegalName()).isEqualTo(CUSTOMER_DTO.getLegalName());
                assertThat(customer.getCommercialName()).isEqualTo(CUSTOMER_DTO.getCommercialName());
                assertThat(customer.getContactPerson()).isEqualTo(CUSTOMER_DTO.getContactPerson());
                assertThat(customer.getVatNr()).isEqualTo(CUSTOMER_DTO.getVatNr());
            });
        }
    }

    @Nested
    class CreateCustomer {
        @Test
        void testCreateNewCustomer() {
            CustomerContentRequest request = new CustomerContentRequest("commercial", "legal", "contact", "vatNr");
            controller.createCustomer(request);

            assertThat(createCustomer.getCustomerDto().getCommercialName()).isEqualTo(request.getCommercialName());
            assertThat(createCustomer.getCustomerDto().getLegalName()).isEqualTo(request.getLegalName());
            assertThat(createCustomer.getCustomerDto().getContactPerson()).isEqualTo(request.getContactPerson());
            assertThat(createCustomer.getCustomerDto().getVatNr()).isEqualTo(request.getVatNr());
        }
    }

    @Nested
    class UpdateCustomer {
        @Test
        void testUpdateCustomer() {
            CustomerContentRequest request = new CustomerContentRequest("commercial", "legal", "contact", "vatNr");
            CustomerId id = CustomerId.newId();
            controller.updateCustomer(id.toString(), request);

            assertThat(updateCustomer.getCustomerId()).isEqualTo(id);
            assertThat(updateCustomer.getCustomerDto().getCommercialName()).isEqualTo(request.getCommercialName());
            assertThat(updateCustomer.getCustomerDto().getLegalName()).isEqualTo(request.getLegalName());
            assertThat(updateCustomer.getCustomerDto().getContactPerson()).isEqualTo(request.getContactPerson());
            assertThat(updateCustomer.getCustomerDto().getVatNr()).isEqualTo(request.getVatNr());
        }
    }

    @Nested
    class DeleteCustomer {
        @Test
        void testDeleteCustomer() {
            CustomerId id = CustomerId.newId();
            controller.deleteCustomer(id.toString());

            assertThat(deleteCustomer.getCustomerId()).isEqualTo(id);
        }
    }
}