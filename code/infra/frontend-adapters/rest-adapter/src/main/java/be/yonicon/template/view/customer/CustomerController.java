package be.yonicon.template.view.customer;

import be.yonicon.template.customer.CreateCustomer;
import be.yonicon.template.customer.DeleteCustomer;
import be.yonicon.template.customer.GetCustomer;
import be.yonicon.template.customer.ListCustomer;
import be.yonicon.template.customer.UpdateCustomer;
import be.yonicon.template.view.ResourceNotFoundException;
import be.yonicon.template.view.customer.content.request.CustomerContentRequest;
import be.yonicon.template.view.customer.content.request.CustomerContentRequestMapper;
import be.yonicon.template.view.customer.content.response.CustomerContentResponse;
import be.yonicon.template.view.customer.content.response.CustomerContentResponseMapper;
import be.yonicon.template.vocabulary.CustomerId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Optional.ofNullable;

@RestController
public class CustomerController implements CustomerRestApi {
    private final CreateCustomer createCustomer;
    private final DeleteCustomer deleteCustomer;
    private final UpdateCustomer updateCustomer;
    private final ListCustomer listCustomer;
    private final GetCustomer getCustomer;

    public CustomerController(CreateCustomer createCustomer,
                              DeleteCustomer deleteCustomer,
                              UpdateCustomer updateCustomer,
                              ListCustomer listCustomer,
                              GetCustomer getCustomer) {
        this.createCustomer = createCustomer;
        this.deleteCustomer = deleteCustomer;
        this.updateCustomer = updateCustomer;
        this.listCustomer = listCustomer;
        this.getCustomer = getCustomer;
    }

    @GetMapping(value = "/customer", produces = "application/json")
    public List<CustomerContentResponse> getCustomers() {
        return listCustomer
                .getAllCustomers()
                .stream()
                .map(CustomerContentResponseMapper::map)
                .toList();
    }

    @GetMapping(value = "/customer/{customerId}", produces = "application/json")
    public CustomerContentResponse getCustomer(@PathVariable("customerId") final String customerId) {
        return ofNullable(customerId)
                .map(CustomerId::from)
                .map(getCustomer::getCustomer)
                .map(CustomerContentResponseMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", customerId));
    }

    @PostMapping(value = "/customer", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@Valid @RequestBody final CustomerContentRequest customerContent) {
        ofNullable(customerContent)
                .map(CustomerContentRequestMapper::map)
                .ifPresent(it -> createCustomer.create(it, new RestCreateCustomerPresenter()));
    }

    @PutMapping(value = "/customer/{customerId}", consumes = "application/json")
    public void updateCustomer(@PathVariable("customerId") final String customerId, @Valid @RequestBody final CustomerContentRequest customerContent) {
        ofNullable(customerContent)
                .map(CustomerContentRequestMapper::map)
                .ifPresent(it -> updateCustomer.update(toCustomerId(customerId), it, new RestUpdateCustomerPresenter()));
    }

    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") final String customerId) {
        deleteCustomer.delete(
                toCustomerId(customerId),
                new RestDeleteCustomerPresenter());
    }

    private CustomerId toCustomerId(final String customerId) {
        return ofNullable(customerId)
                .map(CustomerId::from)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", customerId));
    }
}
