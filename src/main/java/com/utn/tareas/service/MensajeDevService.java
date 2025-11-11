// Archivo: src/main/java/com/utn/tareas/service/MensajeDevService.java
package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev") // Solo se carga con el perfil "dev" [cite: 1060]
public class MensajeDevService implements MensajeService {

    @Override
    public String mostrarBienvenida() {
        return "\n*** [PROFILE: DEV] Bienvenido al entorno de desarrollo. Hot Reload activado. ¡A codear! ***\n";
    }

    @Override
    public String mostrarDespedida() {
        return "\n*** [PROFILE: DEV] Fin de la sesión de desarrollo. ¡Hasta la próxima iteración! ***\n";
    }
}