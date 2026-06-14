# RedNorte - Microservicio de Notificaciones (ms-notificaciones)

El Microservicio de Notificaciones es el encargado de enviar avisos por correo electrónico a los pacientes del sistema **RedNorte** cuando agendan o cancelan una cita médica.

## Características Principales

*   **Envío de Correos Automático**: Notifica al paciente los detalles de su reserva (Médico, Especialidad, Fecha/Hora, Centro Médico).
*   **Integración con EmailJS**: Consume servicios externos de EmailJS para realizar la entrega de correos de forma confiable.
*   **Filtro de Seguridad Interno**: Valida que las llamadas REST provengan de microservicios autorizados a través de una cabecera de autenticación con `INTERNAL_KEY`.

## Tecnologías Utilizadas

*   **Java 21**
*   **Spring Boot 3.4.1**
*   **Spring Web Client & HTTP**
*   **Spring Security & Resource Server**
*   **Spring Dotenv**
*   **Lombok**

## Requisitos Previos

*   Java 21 o superior.
*   Maven 3.8 o superior.
*   Archivo de variables de entorno `.env` en este directorio.

## Variables de Entorno (.env)

Crea un archivo `.env` en la raíz de este directorio con la siguiente estructura de variables (reemplaza los valores entre `<>` con tus configuraciones correspondientes):

```env
# Puerto en el que se ejecutará el microservicio de Notificaciones (por defecto 8085)
PORT=<puerto_ms_notificaciones>

# URL JDBC de conexión a PostgreSQL (ej: jdbc:postgresql://<host>:<port>/<dbname>)
DB_URL=<jdbc_conexion_postgresql>

# Nombre de usuario para la base de datos PostgreSQL
DB_USERNAME=<usuario_database>

# Contraseña del usuario de la base de datos PostgreSQL
DB_PASSWORD=<password_database>

# Llave de comunicación interna compartida entre microservicios
INTERNAL_KEY=<llave_comunicacion_interna>

# ID del servicio configurado en EmailJS (ej: gmail_rednorte)
EMAILJS_SERVICE_ID=<emailjs_service_id>

# Clave pública de la API de EmailJS
EMAILJS_PUBLIC_KEY=<emailjs_public_key>

# Clave privada de la API de EmailJS
EMAILJS_PRIVATE_KEY=<emailjs_private_key>

# URL base del endpoint de envío de correo de EmailJS (ej: https://api.emailjs.com/api/v1.0/email/send)
EMAILJS_API_URL=<emailjs_api_url>
```

## Instrucciones de Ejecución

### Desarrollo Local

Para iniciar el microservicio localmente en el puerto `8085`:

```bash
./mvnw spring-boot:run
```

### Ejecutar Pruebas

Para correr las pruebas unitarias:

```bash
./mvnw test -Dnet.bytebuddy.experimental=true
```

## Dockerización

Construir la imagen de Docker:

```bash
docker build -t rednorte-ms-notificaciones .
```
