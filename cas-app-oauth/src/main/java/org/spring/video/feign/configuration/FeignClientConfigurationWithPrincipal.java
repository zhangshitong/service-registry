//package org.spring.video.feign.configuration;
//
//import feign.RequestInterceptor;
//import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
//
///**
// * Created by stzhang on 2016/9/20.
// */
//@Configuration
//public class FeignClientConfigurationWithPrincipal {
//
//    @Bean
//    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext){
//        return new FeignOAuth2Interceptor(oauth2ClientContext);
//    }
//
//}
