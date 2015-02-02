package io.pase.spring.mvc.cookie.example;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

public class ExampleCookieArgumentResolver implements WebArgumentResolver {

  public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) {
    if (methodParameter.getParameterType().equals(ExampleCookie.class)) {
      return ((HttpServletRequest) webRequest).getAttribute(ExampleCookie.class.getName());
    }
    return UNRESOLVED;
  }
}