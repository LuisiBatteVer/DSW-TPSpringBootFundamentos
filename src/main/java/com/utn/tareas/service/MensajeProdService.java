// Archivo: src/main/java/com/utn/tareas/service/MensajeProdService.java
package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod") // Solo se carga con el perfil "prod" [cite: 1064]
public class MensajeProdService implements MensajeService {

    @Override
    public String mostrarBienvenida() {
        return "\n*** [PROFILE: PROD] Sistema de tareas en ejecución. Operación estable. ***\n";
    }

    @Override
    public String mostrarDespedida() {
        return "\n*** [PROFILE: PROD] Proceso finalizado. Gracias. ***\n";
    }
}