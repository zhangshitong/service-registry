package org.spring.video.feign.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

/**
 * Created by stzhang on 2016/9/20.
 */
@Configuration
public class FeignClientConfiguration {

    @Value("${security.oauth2.client.userAuthorizationUri}")
    private String authorizeUrl;

    @Value("${security.oauth2.client.accessTokenUri}")
    private String tokenUrl;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @Value("${security.oauth2.resource.id}")
    private String resourceId;


    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext){
        return new OAuth2FeignRequestInterceptor(oauth2ClientContext, resource());
    }

    protected OAuth2ProtectedResourceDetails resource() {
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setAccessTokenUri(tokenUrl);
        resource.setUserAuthorizationUri(authorizeUrl);
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        resource.setId(resourceId);
        return resource;
    }


}