package be.yonicon.template.view.customer;

import be.yonicon.template.view.customer.content.request.CustomerContentRequest;
import be.yonicon.template.view.customer.content.response.CustomerContentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Tag(name = "CustomerRestApi", description = "Customer Rest API Operations")
public interface CustomerRestApi {
    @Operation(description = "Gets a list of all customers", responses = {
            @ApiResponse(responseCode = "200", description = "Customers listed")
    })
    @GetMapping("/customer")
    List<CustomerContentResponse> getCustomers();

    @Operation(description = "Gets the customer by id", responses = {
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/customer/{customerId}")
    CustomerContentResponse getCustomer(@PathVariable final String customerId);

    @Operation(description = "Creates a new customer", responses = {
            @ApiResponse(responseCode = "201", description = "Customer created"),
            @ApiResponse(responseCode = "409", description = "Conflicting input")
    })
    @PostMapping("/customer")
    void createCustomer(@Valid final CustomerContentRequest customerContent);

    @Operation(description = "Updates an existing customer", responses = {
            @ApiResponse(responseCode = "200", description = "Customer updated"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "409", description = "Conflicting input")
    })
    @PutMapping("/customer/{customerId}")
    void updateCustomer(@PathVariable final String customerId, @Valid final CustomerContentRequest customerContent);

    @Operation(description = "Deletes an existing customer", responses = {
            @ApiResponse(responseCode = "200", description = "Customer deleted"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @DeleteMapping("/customer/{customerId}")
    void deleteCustomer(@PathVariable final String customerId);
}
