package cl.rednorte.ms_notificaciones.dto;

import lombok.Data;

@Data
public class NotificacionReservaRequest {
    private Long pacienteId;
    private String correoDestino;
    private String nombrePaciente;
    private String nombreCentro;
    private String fechaHoraReserva;
}