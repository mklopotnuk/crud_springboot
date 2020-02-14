package testgroup.crud_springboot.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@PropertySource(value = "classpath:barcode.properties")
public class BarcodeProperties {

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
