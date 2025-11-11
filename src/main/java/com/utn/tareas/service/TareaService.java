// Archivo: src/main/java/com/utn/tareas/service/TareaService.java
package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value; // ¡Importación necesaria!
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareaService {

    // 1. CAMPOS INYECTADOS CON @Value (Configuración)
    @Value("${app.nombre}")
    private String appNombre;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    // 2. CAMPO INYECTADO POR CONSTRUCTOR (Dependencia)
    private final TareaRepository tareaRepository;

    // 3. CONSTRUCTOR
    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    // 4. MÉTODOS DE LÓGICA DE NEGOCIO (Aquí va la lógica que mencionaste)

    /**
     * Agrega una nueva tarea, validando que no se supere el límite (USA maxTareas).
     */
    public Tarea agregarTarea(String descripcion, Prioridad prioridad) {
        // Validación del límite de tareas
        if (tareaRepository.obtenerTodas().size() >= maxTareas) { // <--- Lógica de @Value
            throw new IllegalStateException("ERROR: Se ha alcanzado el límite máximo de tareas (" + maxTareas + ").");
        }

        Tarea nuevaTarea = new Tarea(null, descripcion, false, prioridad);
        return tareaRepository.guardar(nuevaTarea);
    }

    public List<Tarea> listarTodas() {
        return tareaRepository.obtenerTodas();
    }

    public List<Tarea> listarPendientes() {
        return listarTodas().stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    public List<Tarea> listarCompletadas() {
        return listarTodas().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    public Optional<Tarea> marcarComoCompletada(Long id) {
        Optional<Tarea> tareaOpt = tareaRepository.buscarPorId(id);

        tareaOpt.ifPresent(tarea -> {
            tarea.setCompletada(true);
            tareaRepository.guardar(tarea);
        });

        return tareaOpt;
    }

    /**
     * Obtiene estadísticas. La visualización depende de 'mostrarEstadisticas' (USA mostrarEstadisticas).
     */
    public String obtenerEstadisticas() {
        if (!mostrarEstadisticas) { // <--- Lógica de @Value
            return "\n--- Estadísticas ---\nVisualización deshabilitada por configuración (app.mostrar-estadisticas=false)\n--------------------\n";
        }

        List<Tarea> todas = listarTodas();
        long total = todas.size();
        long completadas = todas.stream().filter(Tarea::isCompletada).count();
        long pendientes = total - completadas;

        return String.format(
                "\n--- Estadísticas ---\nTotal: %d | Completadas: %d | Pendientes: %d\n--------------------\n",
                total, completadas, pendientes
        );
    }

    /**
     * Muestra la configuración actual (USA appNombre, maxTareas, mostrarEstadisticas).
     */
    public String mostrarConfiguracion() {
        return String.format(
                "*** CONFIGURACIÓN ACTUAL (Inyectada por @Value) ***\n" +
                        "Aplicación: %s\n" +
                        "Límite Máximo de Tareas: %d\n" +
                        "Mostrar Estadísticas: %s\n" +
                        "**************************************************\n",
                appNombre, maxTareas, mostrarEstadisticas // <--- Variables inyectadas
        );
    }
}