package cl.rednorte.ms_notificaciones.service;

import cl.rednorte.ms_notificaciones.dto.NotificacionRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Value("${emailjs.api-url}")
    private String apiUrl;

    @Value("${emailjs.service-id}")
    private String serviceId;

    @Value("${emailjs.public-key}")
    private String publicKey;

    @Value("${emailjs.private-key}")
    private String privateKey;

    public void enviarNotificacionDinamica(NotificacionRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> payload = new HashMap<>();
        payload.put("service_id", serviceId);
        payload.put("template_id", request.getTemplateId());
        payload.put("user_id", publicKey);
        payload.put("accessToken", privateKey);
        
        Map<String, String> templateParams = request.getParams();
        templateParams.put("to_email", request.getToEmail());
        
        payload.put("template_params", templateParams);

        restTemplate.postForEntity(apiUrl, payload, String.class);
    }
}