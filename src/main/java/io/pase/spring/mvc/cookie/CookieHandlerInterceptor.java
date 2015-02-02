package io.pase.spring.mvc.cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.stream.Stream;

public class CookieHandlerInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private CookieCutter[] cookieCutters;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    for (CookieCutter cookieCutter : cookieCutters) {
      Optional<Cookie> optionalCookie = findCookie(request.getCookies(), cookieCutter.getCookieName());
      BaseCookie cookie;

      if (optionalCookie.isPresent()) {
        cookie = cookieCutter.cutExisting(optionalCookie.get());
      } else {
        cookie = cookieCutter.cutNew();
      }

      request.setAttribute(cookie.getClass().getName(), cookie);
    }

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) {
    for (CookieCutter cookieCutter : cookieCutters) {
      Optional<Object> maybeCookie = Optional.ofNullable(request.getAttribute(cookieCutter.getCookieName()));

      if (maybeCookie.isPresent()) {
        BaseCookie baseCookie = (BaseCookie) maybeCookie.get();

        Cookie cookie = new Cookie(cookieCutter.getCookieName(), cookieCutter.getCookieAsString(baseCookie));
        cookie.setDomain(baseCookie.getDomain());
        cookie.setMaxAge(baseCookie.getMaxAge());
        cookie.setPath(baseCookie.getPath());

        response.addCookie(cookie);
      }
    }
  }

  private Optional<Cookie> findCookie(Cookie[] cookies, String name) {
    return cookies == null ? Optional.empty() : (Stream.of(cookies).filter(c -> name.equals(c.getName())).findFirst());
  }

}
