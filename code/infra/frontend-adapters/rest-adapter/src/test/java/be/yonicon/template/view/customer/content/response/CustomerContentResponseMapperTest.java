package be.yonicon.template.view.customer.content.response;

import be.yonicon.template.vocabulary.CustomerDTO;
import be.yonicon.template.vocabulary.CustomerId;
import be.yonicon.template.vocabulary.CustomerItem;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerContentResponseMapperTest {
    @Test
    void testMapping() {
        CustomerItem item = CustomerItem.newBuilder()
                .withId(CustomerId.newId())
                .withDto(CustomerDTO.newBuilder()
                        .withCommercialName("commercial")
                        .withLegalName("legal")
                        .withVatNr("vatnr")
                        .withContactPerson("contact")
                        .build())
                .build();
        CustomerContentResponse actual = CustomerContentResponseMapper.map(item);

        assertThat(actual.getId()).isEqualTo(item.getId().toString());
        assertThat(actual.getCommercialName()).isEqualTo(item.getDto().getCommercialName());
        assertThat(actual.getLegalName()).isEqualTo(item.getDto().getLegalName());
        assertThat(actual.getContactPerson()).isEqualTo(item.getDto().getContactPerson());
        assertThat(actual.getVatNr()).isEqualTo(item.getDto().getVatNr());
    }

}