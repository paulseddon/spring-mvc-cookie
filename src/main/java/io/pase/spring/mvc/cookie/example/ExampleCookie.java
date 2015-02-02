package io.pase.spring.mvc.cookie.example;

import io.pase.spring.mvc.cookie.BaseCookie;

public class ExampleCookie extends BaseCookie<String> {

  public ExampleCookie(String name, String domain, int maxAge, String path, String value) {
    super(name, domain, maxAge, path, value);
  }
}
