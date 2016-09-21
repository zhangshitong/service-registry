package com.iSupervisionUi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stzhang on 2016/9/21.
 */
@Configuration
@ConfigurationProperties(prefix = "webApp", ignoreInvalidFields = true)
public class WebApplicationServiceUrlConfiguration {
   private Map<String, String> serviceUrl = new HashMap();

   public Map<String, String> getServiceUrl(){
       return serviceUrl;
   }
}
