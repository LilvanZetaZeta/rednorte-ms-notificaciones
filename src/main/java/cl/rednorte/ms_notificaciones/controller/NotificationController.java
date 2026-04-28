package cl.rednorte.ms_notificaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.rednorte.ms_notificaciones.dto.NotificacionRequest;
import cl.rednorte.ms_notificaciones.service.EmailService;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviar(@RequestBody NotificacionRequest request) {
        emailService.enviarNotificacionDinamica(request);
        return ResponseEntity.ok("Notificación enviada con el template: " + request.getTemplateId());
    }
}