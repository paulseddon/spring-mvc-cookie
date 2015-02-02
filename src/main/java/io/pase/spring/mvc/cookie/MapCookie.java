package io.pase.spring.mvc.cookie;

import java.util.Map;

public class MapCookie extends BaseCookie<Map<String, String>> {

  protected MapCookie(String domain, int maxAge, String path, Map<String, String> value) {
    super(domain, maxAge, path, value);
  }

  protected String get(String key) {
    return getValue().get(key);
  }

  protected void put(String key, String value) {
    getValue().put(key, value);
  }
}
