package io.pase.spring.web.cookie;

public class CookieHandlerInterceptorTest {

//    private CookieHandlerInterceptor interceptor;
//
//    @Mock
//    private CookieCutter cookieCutter;
//
//    @Mock
//    private HttpServletRequest request;
//
//    @Mock
//    private HttpServletResponse response;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        interceptor = new CookieHandlerInterceptor();
//
//        when(cookieCutter.getCookieName()).thenReturn("TEST_COOKIE");
//
//        ReflectionTestUtils.setField(interceptor, "cookieCutters", new CookieCutter[]{cookieCutter});
//    }
//
//    @Test
//    public void preHandleShouldCreateCookieInRequestFromExistingCookie() {
//        Cookie[] cookies = new Cookie[]{new Cookie("TEST_COOKIE", "ABCDEF")};
//        when(request.getCookies()).thenReturn(cookies);
//
//        boolean proceed = interceptor.preHandle(request, response, null);
//
//        verify(request).getCookies();
//        verify(cookieCutter).cutExisting(any(Cookie.class));
//        verify(request).setAttribute(eq("TEST_COOKIE"), any(BaseCookie.class));
//        assertTrue(proceed);
//    }
//
//    @Test
//    public void preHandleShouldCreateCookieInRequest() {
//        boolean proceed = interceptor.preHandle(request, response, null);
//
//        verify(request).getCookies();
//        verify(cookieCutter).cutNew();
//        verify(request).setAttribute(eq("TEST_COOKIE"), any(BaseCookie.class));
//        assertTrue(proceed);
//    }
//
//    @Test
//    public void postHandleShouldWriteCookiesInRequest() {
//        BaseCookie baseCookie = mock(BaseCookie.class);
//        when(baseCookie.getValueAsString()).thenReturn("ABCDEF");
//        when(baseCookie.getDomain()).thenReturn("gumtree.com");
//        when(baseCookie.getMaxAge()).thenReturn(9999999);
//        when(baseCookie.getPath()).thenReturn("/");
//
//        when(request.getAttribute("TEST_COOKIE")).thenReturn(baseCookie);
//
//        interceptor.postHandle(request, response, null, null);
//
//        verify(request).getAttribute("TEST_COOKIE");
//
//        ArgumentCaptor<Cookie> argument = ArgumentCaptor.forClass(Cookie.class);
//        verify(response).addCookie(argument.capture());
//        assertThat(argument.getValue().getDomain(), is("gumtree.com"));
//        assertThat(argument.getValue().getMaxAge(), is(9999999));
//        assertThat(argument.getValue().getPath(), is("/"));
//        assertThat(argument.getValue().getValue(), is("ABCDEF"));
//    }
}
