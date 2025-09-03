package finalmission.feature.customer.controller;

import finalmission.feature.customer.dto.request.CustomerCreateRequest;
import finalmission.feature.customer.dto.response.CustomerResponse;
import finalmission.feature.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> registerUser(@RequestBody CustomerCreateRequest request){
        CustomerResponse customerResponse = customerService.creatUser(request);
        return ResponseEntity.ok(customerResponse);
    }
}
