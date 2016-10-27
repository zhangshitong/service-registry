package org.spring.casapp.properties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderSupport;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by stzhang on 2016/10/27.
 */
public class PropertiesLoaderSupportWithSSL extends PropertiesLoaderSupport implements InitializingBean {
    private static String sslTrustStoreKey = "javax.net.ssl.trustStore";
    private static String sslTrustStorePwdKey = "javax.net.ssl.trustStorePassword";
    private static String CLASSPATH_ALL_URL_PREFIX = "classpath:";

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    @Override
    public void afterPropertiesSet() throws Exception {
         mergeProperties();
    }

    @Override
    protected Properties mergeProperties() throws IOException {
        //super
        Properties p = super.mergeProperties();
        //
        if(p.containsKey(sslTrustStoreKey) && System.getProperty(sslTrustStoreKey) == null){
            String trustStorePath = p.getProperty(sslTrustStoreKey);
            if(trustStorePath.startsWith(resourceLoader.CLASSPATH_URL_PREFIX)){
                 trustStorePath = trustStorePath.substring(resourceLoader.CLASSPATH_URL_PREFIX.length());
            }
            Resource resource = resourceLoader.getResource(trustStorePath);
            String fileAbsolutePath = resource.getFile().getAbsolutePath();
            System.out.println("Find trust keystore: "+ fileAbsolutePath + ", password: ******");
            System.setProperty(sslTrustStoreKey, fileAbsolutePath);
        }
        if(p.containsKey(sslTrustStorePwdKey) && System.getProperty(sslTrustStorePwdKey) == null){
            System.setProperty(sslTrustStorePwdKey, p.getProperty(sslTrustStorePwdKey));
        }
//        System.setProperty("javax.net.ssl.trustStore", truststoreFile);
//        System.setProperty("javax.net.ssl.trustStorePassword",truststorePassword);
        return p;
    }

}
