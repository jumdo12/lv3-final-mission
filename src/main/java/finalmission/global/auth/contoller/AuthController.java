package finalmission.global.auth.contoller;

import finalmission.common.argumentresolver.LoginArgumentResolver;
import finalmission.global.auth.dto.CustomerLoginRequest;
import finalmission.global.auth.dto.LoginCustomerInfo;
import finalmission.global.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증 API", description = "인증 관련 API")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인", description = "로그인합니다.")
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody CustomerLoginRequest loginRequest, HttpSession session) {
        LoginCustomerInfo loginCustomerInfo = authService.loginCustomer(loginRequest);
        session.setAttribute(LoginArgumentResolver.LOGIN_SESSTION_KEY, loginCustomerInfo);
        return ResponseEntity.ok().build();
    }
}
