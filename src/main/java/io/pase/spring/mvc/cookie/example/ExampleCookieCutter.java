package io.pase.spring.mvc.cookie.example;

import io.pase.spring.mvc.cookie.CookieCutter;

import javax.servlet.http.Cookie;

public class ExampleCookieCutter extends CookieCutter<ExampleCookie> {

  @Override
  protected String getCookieName() {
    return "myexamplecookie";
  }

  @Override
  protected ExampleCookie cutExisting(Cookie existingCookie) {
    return new ExampleCookie(getCookieName(), existingCookie.getDomain(), existingCookie.getMaxAge(), existingCookie.getPath(),
      existingCookie.getValue());
  }

  @Override
  protected ExampleCookie cutNew() {
    return new ExampleCookie(getCookieName(), "io.pase", MAX_AGE_COOKIE, "/", "some-default-value");
  }

  @Override
  protected String getCookieAsString(ExampleCookie cookie) {
    return cookie.getValue();
  }
}
