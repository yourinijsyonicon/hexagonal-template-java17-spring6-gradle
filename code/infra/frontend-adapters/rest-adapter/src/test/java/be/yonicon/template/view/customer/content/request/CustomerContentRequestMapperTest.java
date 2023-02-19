package be.yonicon.template.view.customer.content.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerContentRequestMapperTest {
    @Test
    void testMapping() {
        var request = new CustomerContentRequest("commercial", "legal", "contact person", "vatnr");
        var actual = CustomerContentRequestMapper.map(request);

        assertThat(actual.getCommercialName()).isEqualTo(request.getCommercialName());
        assertThat(actual.getLegalName()).isEqualTo(request.getLegalName());
        assertThat(actual.getContactPerson()).isEqualTo(request.getContactPerson());
        assertThat(actual.getVatNr()).isEqualTo(request.getVatNr());


    }
}