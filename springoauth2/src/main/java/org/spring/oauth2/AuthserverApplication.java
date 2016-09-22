package org.spring.oauth2;

import java.io.IOException;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Controller
public class AuthserverApplication extends WebMvcConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(AuthserverApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AuthserverApplication.class, args);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login2");
		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
	}

	@Configuration
	@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
	protected static class LoginConfig extends WebSecurityConfigurerAdapter {
		
//		@Autowired
//		private AuthenticationManager authenticationManager;

		@Autowired
		private UserDetailsService authUserDetailService;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.formLogin().loginPage("/login").permitAll().and().authorizeRequests()
					.anyRequest().authenticated();
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(authUserDetailService);
		}
	}
	
	@Configuration
	@EnableResourceServer
	@RestController
	protected static class ResourceServerConfig  extends ResourceServerConfigurerAdapter {
		
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/oauth/user")
					.authorizeRequests().anyRequest().authenticated().and();
		}
	    
	    @Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			super.configure(resources);
		}
	    
		@RequestMapping("/oauth/user")
		public Principal user(Principal user) {
			return user;
		}
		
		 
	}
	
	
	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {
		
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
					.withClient("clientiop")
					.secret("clientsecretiop")
					.authorizedGrantTypes("authorization_code", "refresh_token",
							"password","client_credentials").scopes("trust").autoApprove(true);
		}
	}
//
//	@Bean
//	public FilterRegistrationBean someFilterRegistration() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(allowOriginFilter());
//		registration.addUrlPatterns("/oauth/authorize");
//		registration.setOrder(1);
//		return registration;
//	}
//
//	@Bean
//	public Filter allowOriginFilter() {
//		return new OncePerRequestFilter() {
//			@Override
//			protected void doFilterInternal(HttpServletRequest request,
//											HttpServletResponse response, FilterChain filterChain)
//					throws ServletException, IOException {
//
//				logger.info("OncePerRequestFilter Access-Control-Allow-Origin:*");
//				response.addHeader("Access-Control-Allow-Origin","*");
//				response.addHeader("Access-Control-Allow-Methods","*");
//				filterChain.doFilter(request, response);
//			}
//		};
//	}

}
