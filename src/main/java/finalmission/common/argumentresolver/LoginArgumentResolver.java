package finalmission.common.argumentresolver;

import finalmission.global.auth.dto.LoginCustomerInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    public static String LOGIN_SESSTION_KEY = "loginCustomer";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(finalmission.global.auth.annotaion.LoginCustomer.class);
        boolean assignableFrom = LoginCustomerInfo.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && assignableFrom;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest nativeRequest = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = nativeRequest.getSession(false);

        if(session == null || session.getAttribute(LOGIN_SESSTION_KEY) == null) {
            throw new IllegalArgumentException("로그인된 사용자가 없습니다.");
        }

        return session.getAttribute(LOGIN_SESSTION_KEY);
    }
}
