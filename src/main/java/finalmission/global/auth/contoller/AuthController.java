package finalmission.global.auth.contoller;

import finalmission.common.argumentresolver.LoginArgumentResolver;
import finalmission.global.auth.dto.CustomerLoginRequest;
import finalmission.global.auth.dto.LoginCustomerInfo;
import finalmission.global.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody CustomerLoginRequest loginRequest, HttpSession session) {
        LoginCustomerInfo loginCustomerInfo = authService.loginCustomer(loginRequest);
        session.setAttribute(LoginArgumentResolver.LOGIN_SESSTION_KEY, loginCustomerInfo);
        return ResponseEntity.ok().build();
    } 
}
