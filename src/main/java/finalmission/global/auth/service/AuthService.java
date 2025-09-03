package finalmission.global.auth.service;

import finalmission.feature.customer.domain.Customer;
import finalmission.feature.customer.repository.CustomerRepository;
import finalmission.global.auth.dto.CustomerLoginRequest;
import finalmission.global.auth.dto.LoginCustomerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;

    public LoginCustomerInfo loginCustomer(CustomerLoginRequest customerLoginRequest) {
        Customer loginCustomer = customerRepository.findByEmailAndPassword(customerLoginRequest.email(), customerLoginRequest.password())
                .orElseThrow(() -> new IllegalStateException("아이디 또는 비밀번호가 올바르지 않습니다"));
        return new LoginCustomerInfo(loginCustomer.getId(), loginCustomer.getName());
    }
}
