package co.edu.unicauca.gesrotesbackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  Configuración de CORS para permitir solicitudes de origen cruzado desde el frontend.
*/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura el registro CORS para permitir solicitudes desde el origen localhost:3000
     * con los métodos GET, POST, PUT, DELETE y OPTIONS.
     * Permite credenciales y establece el tiempo de caché en 3600 segundos.
     * @param registry registro CORS
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("")
                .allowCredentials(true)
                .maxAge(3600);
    }
}