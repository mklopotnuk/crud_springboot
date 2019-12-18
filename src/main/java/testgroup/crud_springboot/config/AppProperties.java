package testgroup.crud_springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppProperties {

    @Value("${urlBarcodeGenerator}")
    private String urlBarcodeGenerator;

    @Value("${barcodeFileFormat}")
    private String barcodeFileFormat;

    @Value("${barcodeResolution}")
    private String barcodeResolution;

    public String getUrlBarcodeGenerator() {
        return urlBarcodeGenerator;
    }

    public String getBarcodeFileFormat() {
        return barcodeFileFormat;
    }

    public String getBarcodeResolution() {
        return barcodeResolution;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
