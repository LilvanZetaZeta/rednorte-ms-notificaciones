package cl.rednorte.ms_notificaciones.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "emailjs")
@Data
public class EmailJsConfig {
    private String serviceId;
    private String publicKey;
}