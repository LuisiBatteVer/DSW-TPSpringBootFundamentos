// Archivo: src/main/java/com/utn/tareas/TareasApplication.java
package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner; // Importar la interfaz
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 1. Implementa CommandLineRunner
@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

    // Usamos SLF4J (logging de Spring Boot) para imprimir en consola de forma limpia
    private static final Logger log = LoggerFactory.getLogger(TareasApplication.class);

    private final TareaService tareaService;
    private final MensajeService mensajeService;

    // 2. Inyección de Dependencias por Constructor
    // Spring inyecta automáticamente TareaService y MensajeService (el bean dev o prod)
    public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
        this.tareaService = tareaService;
        this.mensajeService = mensajeService;
    }

    public static void main(String[] args) {
        // Ejecuta la aplicación y obtiene el contexto
        ConfigurableApplicationContext context = SpringApplication.run(TareasApplication.class, args);

        // Detenemos la aplicación inmediatamente después de ejecutar run(),
        // ya que el TP solo requiere una ejecución en consola.
        SpringApplication.exit(context, () -> 0);
    }

    // 3. Método run(...) - Contiene la lógica de prueba
    @Override
    public void run(String... args) throws Exception {

        // 1. Mostrar mensaje de bienvenida (usando MensajeService) [cite: 1278]
        log.info(mensajeService.mostrarBienvenida());

        // 2. Mostrar la configuración actual [cite: 1279]
        log.info(tareaService.mostrarConfiguracion());

        log.info("--- 3. Listando todas las tareas iniciales ---");
        // 3. Listar todas las tareas iniciales [cite: 1280]
        tareaService.listarTodas().forEach(tarea -> log.info("  -> {}", tarea));

        // 4. Agregar una nueva tarea (Se probará la validación de límite) [cite: 1281]
        try {
            log.info("\n--- 4. Intentando agregar una nueva tarea (ALTA) ---");
            // Si el profile es 'dev', esta puede fallar si ya hay 10 tareas iniciales
            Tarea nueva = tareaService.agregarTarea("Terminar la documentación del TP", Prioridad.ALTA);
            log.info("  -> Tarea agregada con éxito: {}", nueva);

            Tarea otra = tareaService.agregarTarea("Revisar el README.md", Prioridad.BAJA);
            log.info("  -> Tarea agregada con éxito: {}", otra);

        } catch (IllegalStateException e) {
            log.warn("\n--- 4. ADVERTENCIA: No se pudo agregar la tarea ---");
            log.warn("  -> MENSAJE: {}", e.getMessage());
        }

        log.info("\n--- 5. Listando tareas PENDIENTES ---");
        // 5. Listar tareas pendientes [cite: 1282]
        tareaService.listarPendientes().forEach(tarea -> log.info("  -> {}", tarea));

        // 6. Marcar una tarea como completada (ID 1 fue probablemente "Revisar los conceptos de DI...") [cite: 1283]
        Long idACompletar = 3L;
        tareaService.marcarComoCompletada(idACompletar);
        log.info("\n--- 6. Tarea con ID {} marcada como COMPLETADA ---", idACompletar);

        // 7. Mostrar estadísticas (comportamiento condicional por profile) [cite: 1284]
        log.info(tareaService.obtenerEstadisticas());

        log.info("--- 8. Listando tareas COMPLETADAS ---");
        // 8. Listar tareas completadas [cite: 1285]
        tareaService.listarCompletadas().forEach(tarea -> log.info("  -> {}", tarea));

        // 9. Mostrar mensaje de despedida [cite: 1286]
        log.info(mensajeService.mostrarDespedida());
    }
}