package cl.rednorte.ms_notificaciones.controller;

import cl.rednorte.ms_notificaciones.dto.NotificacionReservaRequest;
import cl.rednorte.ms_notificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping("/reserva")
    public ResponseEntity<String> notificarReserva(@RequestBody NotificacionReservaRequest request) {
        notificacionService.enviarCorreoReserva(request);
        return ResponseEntity.ok("Notificación procesada y registrada para el paciente ID: " + request.getPacienteId());
    }
}