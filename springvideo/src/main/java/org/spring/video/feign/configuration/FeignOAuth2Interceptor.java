//package org.spring.video.feign.configuration;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//
///**
// * Created by stzhang on 2016/9/20.
// */
//public class FeignOAuth2Interceptor implements RequestInterceptor {
//
//    public static final String BEARER = "Bearer";
//    public static final String AUTHORIZATION = "Authorization";
//
//    private OAuth2ClientContext oauth2ClientContext;
//
//    public FeignOAuth2Interceptor(OAuth2ClientContext oauth2ClientContext) {
//        this.oauth2ClientContext = oauth2ClientContext;
//    }
//
//    @Override
//    public void apply(feign.RequestTemplate template) {
//        if (!template.headers().containsKey(AUTHORIZATION)
//                && oauth2ClientContext.getAccessTokenRequest().getExistingToken() != null) {
//            template.header(AUTHORIZATION, String.format("%s %s", BEARER,
//                    oauth2ClientContext.getAccessTokenRequest().getExistingToken().toString()));
//        }
//    }
//}
