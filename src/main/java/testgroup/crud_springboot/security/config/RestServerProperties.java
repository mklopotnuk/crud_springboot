package testgroup.crud_springboot.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:restserver.properties")
public class RestServerProperties {
    @Value("${restServer}")
    private String url;

    @Value("${restPort}")
    private  String port;

    public String getUrl() {
        return url;
    }

    public String getPort() {
        return port;
    }
}
