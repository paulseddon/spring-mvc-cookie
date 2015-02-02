package io.pase.spring.mvc.cookie;

import javax.servlet.http.Cookie;

public abstract class CookieCutter<T extends BaseCookie> {

  public static final int MAX_AGE_COOKIE = 157784630;

  protected abstract String getCookieName();

  protected abstract T cutExisting(Cookie existingCookie);

  protected abstract T cutNew();

  protected abstract String getCookieAsString(T cookie);

}
