package cl.rednorte.ms_notificaciones.dto;

import lombok.Data;
import java.util.Map;

@Data
public class NotificacionRequest {
    private String templateId;      // Aquí pasas el ID del template que quieras usar
    private String toEmail;         // Correo del destinatario
    private Map<String, String> params; // Todos los campos dinámicos de tu template (nombre, fecha, etc.)
}