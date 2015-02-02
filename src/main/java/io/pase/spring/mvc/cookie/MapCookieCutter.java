package io.pase.spring.mvc.cookie;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

import javax.servlet.http.Cookie;
import java.util.Map;
import java.util.stream.Stream;

public class MapCookieCutter extends CookieCutter<MapCookie> {

  private static final Splitter FIELD_SPLITTER = Splitter.on("|").omitEmptyStrings().trimResults();
  private static final Joiner.MapJoiner FIELD_PAIR_JOINER = Joiner.on('|').withKeyValueSeparator(":");

  @Override
  protected String getCookieName() {
    return "MyMapCookie";
  }

  @Override
  protected MapCookie cutExisting(Cookie existingCookie) {
    return new MapCookie(getCookieName(), existingCookie.getDomain(), existingCookie.getMaxAge(), existingCookie.getPath(),
      deserializeCookieMap(existingCookie.getValue()));
  }

  @Override
  protected MapCookie cutNew() {
    return new MapCookie(getCookieName(), "io.pase", MAX_AGE_COOKIE, "/", Maps.newHashMap());
  }

  @Override
  protected String getCookieAsString(MapCookie cookie) {
    return FIELD_PAIR_JOINER.join(cookie.getValue());
  }

  private Map<String, String> deserializeCookieMap(String cookieValue) {
    Map<String, String> cookieMap = Maps.newHashMap();

    if (cookieValue != null) {
      Stream<Pair> pairs = FIELD_SPLITTER.splitToList(cookieValue).stream().map(f -> {
        String[] pair = f.split(":");
        return new Pair(pair[0], pair[1]);
      });

      // side effect :(
      pairs.forEach(p -> cookieMap.put(p.a, p.b));
    }

    return cookieMap;
  }

  class Pair {
    String a;
    String b;

    Pair(String a, String b) {
      this.a = a;
      this.b = b;
    }
  }
}
