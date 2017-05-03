package com.codeMst.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by stzhang on 2016/9/21.
 */
@Configuration
@ComponentScan
public class ResourceServerApplication {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfig  extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/code/**").authorizeRequests().anyRequest()
                    .authenticated();
        }
    }


    @RestController
    public static class UserPrincipalContrl {
        @RequestMapping("/code/user")
        public Principal user(Principal user) {
            return user;
        }

    }

    @RestController
    public static class UserPrincipalCtl {
        @RequestMapping("/fetch/user")
        public Principal user(HttpServletRequest request) {
            Principal user = request.getUserPrincipal();
            return user;

//            SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

    }
}
