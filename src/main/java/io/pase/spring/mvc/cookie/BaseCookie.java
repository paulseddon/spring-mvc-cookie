package io.pase.spring.mvc.cookie;

public abstract class BaseCookie<T> {

  private final String domain;
  private final int maxAge;
  private final String path;
  private final T value;

  protected BaseCookie(String domain, int maxAge, String path, T value) {
    this.domain = domain;
    this.maxAge = maxAge;
    this.path = path;
    this.value = value;
  }

  public String getDomain() {
    return domain;
  }

  public int getMaxAge() {
    return maxAge;
  }

  public String getPath() {
    return path;
  }

  protected T getValue() {
    return value;
  }
}
