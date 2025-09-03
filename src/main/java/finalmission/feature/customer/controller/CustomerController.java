package finalmission.feature.customer.controller;

import finalmission.feature.customer.dto.request.CustomerCreateRequest;
import finalmission.feature.customer.dto.response.CustomerResponse;
import finalmission.feature.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "고객 API", description = "고객 관련 API")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "고객 등록", description = "고객을 등록합니다.")
    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> registerUser(@RequestBody CustomerCreateRequest request){
        CustomerResponse customerResponse = customerService.creatUser(request);
        return ResponseEntity.ok(customerResponse);
    }
}
