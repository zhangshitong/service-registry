package org.spring.video.properties;

import com.sun.org.apache.xml.internal.security.Init;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.UUID;

/**
 * Created by stzhang on 2016/10/28.
 */
@ConfigurationProperties(prefix = "javax.net.ssl", ignoreInvalidFields = false)
public class SystemSSLPropertiesConfiguration implements InitializingBean{
    public static class Constants {
        //static
        public static String sslTrustStoreKey = "javax.net.ssl.trustStore";
        public static String sslTrustStorePwdKey = "javax.net.ssl.trustStorePassword";
    }

    public Resource trustStore = null;
    public String trustStorePassword = UUID.randomUUID().toString();

    public void setTrustStore(Resource trustStore) {
        this.trustStore = trustStore;
    }

    public void setTrustStorePassword(String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
    }
//
    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println("---afterPropertiesSet------------------------------------------");
//        System.out.println("System " + Constants.sslTrustStorePwdKey + "= " + System.getProperty( Constants.sslTrustStorePwdKey));
//        System.out.println("System " +  Constants.sslTrustStoreKey + "= " + System.getProperty( Constants.sslTrustStoreKey));
//        System.out.println("---------------------------------------------");
//        System.out.println( Constants.sslTrustStoreKey + "= " + trustStore);
//        System.out.println( Constants.sslTrustStorePwdKey + "= " + trustStorePassword);
        if(trustStore != null && System.getProperty( Constants.sslTrustStoreKey) == null){
            String fileAbsolutePath = trustStore.getFile().getAbsolutePath();
            System.out.println("Find trust keystore: "+ fileAbsolutePath + ", password: ******");
            System.setProperty( Constants.sslTrustStoreKey, fileAbsolutePath);
        }
        if(trustStorePassword !=null && System.getProperty( Constants.sslTrustStorePwdKey) == null){
            System.out.println("Find trust keystore password: "+ ", password: ******");
            System.setProperty( Constants.sslTrustStorePwdKey, trustStorePassword);
        }
    }


}
