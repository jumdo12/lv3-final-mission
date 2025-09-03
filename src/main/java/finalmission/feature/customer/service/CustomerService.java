package finalmission.feature.customer.service;

import finalmission.feature.customer.domain.Customer;
import finalmission.feature.customer.dto.request.CustomerCreateRequest;
import finalmission.feature.customer.dto.response.CustomerResponse;
import finalmission.feature.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse creatUser(CustomerCreateRequest request) {
        Customer withoutId = Customer.createWithoutId(request.name(), request.email(), request.password());
        Customer savedCustomer = customerRepository.save(withoutId);
        return new CustomerResponse(savedCustomer.getId(), savedCustomer.getEmail());
    }
}
