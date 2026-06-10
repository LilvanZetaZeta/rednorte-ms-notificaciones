package cl.rednorte.ms_notificaciones.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import cl.rednorte.ms_notificaciones.dto.NotificacionReservaRequest;
import cl.rednorte.ms_notificaciones.entity.LogComunicacion;
import cl.rednorte.ms_notificaciones.repository.LogComunicacionRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

@SpringBootTest
public class NotificacionServiceTest {

    @Autowired
    private NotificacionService service;

    @MockBean
    private LogComunicacionRepository logRepository;

    @MockBean
    private RestOperations restTemplate;

    private NotificacionReservaRequest createMockRequest() {
        NotificacionReservaRequest request = new NotificacionReservaRequest();
        request.setPacienteId(10L);
        request.setCorreoDestino("paciente@example.com");
        request.setNombrePaciente("Pedro Pascal");
        request.setNombreCentro("Centro Sur");
        request.setFechaHoraReserva("2026-06-10 12:00");
        return request;
    }

    @Test
    public void testEnviarCorreoReserva_Success() {
        // Preparar
        NotificacionReservaRequest request = createMockRequest();
        ResponseEntity<String> response = new ResponseEntity<>("Success", HttpStatus.OK);

        when(restTemplate.postForEntity(anyString(), any(Map.class), eq(String.class)))
                .thenReturn(response);

        // Actuar
        service.enviarCorreoReserva(request);

        // Verificar
        verify(logRepository, times(1)).save(argThat(log -> log.getPacienteId().equals(10L) &&
                log.getTipoEvento().equals("Reserva") &&
                log.getProveedorId().equals("EmailJS") &&
                log.getEstadoEnvio() == LogComunicacion.EstadoEnvio.ENVIADO));
    }

    @Test
    public void testEnviarCorreoReserva_FailureResponse() {
        // Preparar
        NotificacionReservaRequest request = createMockRequest();
        ResponseEntity<String> response = new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

        when(restTemplate.postForEntity(anyString(), any(Map.class), eq(String.class)))
                .thenReturn(response);

        // Actuar
        service.enviarCorreoReserva(request);

        // Verificar
        verify(logRepository, times(1)).save(argThat(log -> log.getPacienteId().equals(10L) &&
                log.getEstadoEnvio() == LogComunicacion.EstadoEnvio.FALLIDO));
    }

    @Test
    public void testEnviarCorreoReserva_Exception() {
        // Preparar
        NotificacionReservaRequest request = createMockRequest();

        when(restTemplate.postForEntity(anyString(), any(Map.class), eq(String.class)))
                .thenThrow(new RuntimeException("Connection timeout"));

        // Actuar
        service.enviarCorreoReserva(request);

        // Verificar
        verify(logRepository, times(1)).save(argThat(log -> log.getPacienteId().equals(10L) &&
                log.getEstadoEnvio() == LogComunicacion.EstadoEnvio.FALLIDO));
    }
}
