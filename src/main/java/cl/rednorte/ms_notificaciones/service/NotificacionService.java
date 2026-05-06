package cl.rednorte.ms_notificaciones.service;

import cl.rednorte.ms_notificaciones.dto.NotificacionReservaRequest;
import cl.rednorte.ms_notificaciones.entity.LogComunicacion;
import cl.rednorte.ms_notificaciones.repository.LogComunicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotificacionService {

    @Autowired
    private LogComunicacionRepository logRepository;

    @Value("${emailjs.api-url}") private String apiUrl;
    @Value("${emailjs.service-id}") private String serviceId;
    @Value("${emailjs.templates.reserva}") private String templateReserva;
    @Value("${emailjs.public-key}") private String publicKey;
    @Value("${emailjs.private-key}") private String privateKey;

    public void enviarCorreoReserva(NotificacionReservaRequest request) {
        LogComunicacion log = new LogComunicacion();
        log.setPacienteId(request.getPacienteId());
        log.setTipoEvento("Reserva");
        log.setProveedorId("EmailJS");
        log.setFechaEnvio(LocalDateTime.now());

        try {
            
            Map<String, Object> emailJsBody = new HashMap<>();
            emailJsBody.put("service_id", serviceId);
            emailJsBody.put("template_id", templateReserva);
            emailJsBody.put("user_id", publicKey);
            emailJsBody.put("accessToken", privateKey);

            
            Map<String, String> templateParams = new HashMap<>();
            templateParams.put("to_email", request.getCorreoDestino());
            templateParams.put("to_name", request.getNombrePaciente());
            templateParams.put("centro_medico", request.getNombreCentro());
            templateParams.put("fecha_hora", request.getFechaHoraReserva());
            
            emailJsBody.put("template_params", templateParams);

           
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, emailJsBody, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.setEstadoEnvio(LogComunicacion.EstadoEnvio.ENVIADO);
            } else {
                log.setEstadoEnvio(LogComunicacion.EstadoEnvio.FALLIDO);
            }

        } catch (Exception e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
            log.setEstadoEnvio(LogComunicacion.EstadoEnvio.FALLIDO);
        }

  
        logRepository.save(log);
    }
}