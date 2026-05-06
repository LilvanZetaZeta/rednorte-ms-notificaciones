package cl.rednorte.ms_notificaciones.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "log_comunicacion")
public class LogComunicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "tipo_evento", nullable = false, length = 50)
    private String tipoEvento; // Ej: 'Reserva', 'Alerta'

    @Column(name = "proveedor_id", length = 100)
    private String proveedorId; // Aquí guardaremos "EmailJS" o algún ID de seguimiento

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_envio", nullable = false)
    private EstadoEnvio estadoEnvio;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;

    public enum EstadoEnvio {
        ENVIADO, FALLIDO
    }
}