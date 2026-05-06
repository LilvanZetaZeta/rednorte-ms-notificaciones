package cl.rednorte.ms_notificaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.rednorte.ms_notificaciones.entity.LogComunicacion;

public interface LogComunicacionRepository extends JpaRepository<LogComunicacion, Long> {
  
}
