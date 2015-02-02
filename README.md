#Spring MVC Cookie

Simple framework for making cookies easier to use in Spring MVC, effectively 'strongly typing' them to be used in your controllers. Serves as an alternative to @CookieValue.

The reading and writing* of your cookies is taken care of in the framework. You just express them as a parameter to a controller method.

Create Cookie (extends from BaseCookie) and define its value type. Simplest example is storing a string in the cookie.

```java
public class ExampleStringCookie extends BaseCookie<String> {

  public ExampleStringCookie(String domain, int maxAge, String path, String value) {
    super(domain, maxAge, path, value);
  }
}
```

Create Cookie Cutter (extend from CookieCutter).

```java
public class ExampleCookieCutter extends CookieCutter<ExampleStringCookie> {

  @Override
  protected String getCookieName() {
    return "myexamplecookie";
  }

  @Override
  protected ExampleStringCookie cutExisting(Cookie existingCookie) {
    return new ExampleStringCookie(existingCookie.getDomain(), existingCookie.getMaxAge(), existingCookie.getPath(),
      existingCookie.getValue());
  }

  @Override
  protected ExampleStringCookie cutNew() {
    return new ExampleStringCookie("io.pase", MAX_AGE_COOKIE, "/", "some-default-value");
  }

  @Override
  protected String getCookieAsString(ExampleStringCookie cookie) {
    return cookie.getValue();
  }
}
```

Register CookieCutter with Spring context (will autowire into CookieHandlerInterceptor).

WebArgumentResolver for Cookie, ensures your Cookie is available as a parameter in controller.

```java
public class ExampleCookieArgumentResolver implements WebArgumentResolver {

  public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) {
    if (methodParameter.getParameterType().equals(ExampleCookie.class)) {
      return ((HttpServletRequest) webRequest).getAttribute(ExampleCookie.class.getName());
    }
    return UNRESOLVED;
  }
}
```

Use in controller method.

```java
@RequestMapping(value="/action")
public String handleRequest(ExampleCookie myCookie){
    String cookieValue = myCookie.getValue()
    //etc
} 
```

*Ideally Cookie would be immutable, needs refinement.