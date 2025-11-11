package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository // Etiqueta semántica: Indica que gestiona el acceso a datos. 
public class TareaRepository {

    private final List<Tarea> tareas; // Almacenamiento de tareas en memoria 
    private final AtomicLong idGenerator; // Ayuda para generar IDs automáticos [cite: 224]

    // Constructor: Inicializa la lista con tareas de ejemplo [cite: 215]
    public TareaRepository() {
        this.tareas = new ArrayList<>();
        this.idGenerator = new AtomicLong(0);

        // Tareas de ejemplo iniciales (3-5 tareas de ejemplo) [cite: 215]
        guardar(new Tarea(null, "Revisar los conceptos de DI y Beans", true, Prioridad.ALTA));
        guardar(new Tarea(null, "Implementar la lógica de negocio en TareaService", false, Prioridad.MEDIA));
        guardar(new Tarea(null, "Crear application.properties para la Parte 4", false, Prioridad.BAJA));
        guardar(new Tarea(null, "Probar la ejecución con CommandLineRunner", false, Prioridad.ALTA));
    }

    /**
     * Guarda una tarea, asignando un ID automático si es nueva. [cite: 216]
     */
    public Tarea guardar(Tarea tarea) {
        if (tarea.getId() == null) {
            tarea.setId(idGenerator.incrementAndGet()); // Generar ID automático [cite: 224]
        }

        // Lógica para agregar o "actualizar" en memoria
        boolean exists = tareas.stream().anyMatch(t -> t.getId().equals(tarea.getId()));
        if (!exists) {
            tareas.add(tarea);
        } else {
            // En un repo en memoria, se simula una actualización
            tareas.stream()
                    .filter(t -> t.getId().equals(tarea.getId()))
                    .findFirst()
                    .ifPresent(t -> {
                        t.setDescripcion(tarea.getDescripcion());
                        t.setCompletada(tarea.isCompletada());
                        t.setPrioridad(tarea.getPrioridad());
                    });
        }
        return tarea;
    }

    /**
     * Obtiene una copia de todas las tareas. [cite: 217]
     */
    public List<Tarea> obtenerTodas() {
        return new ArrayList<>(tareas);
    }

    /**
     * Busca una tarea por ID. Retorna Optional para manejar si no se encuentra. [cite: 218]
     */
    public Optional<Tarea> buscarPorId(Long id) {
        return tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    /**
     * Elimina una tarea por ID. [cite: 219]
     */
    public void eliminarPorId(Long id) {
        tareas.removeIf(t -> t.getId().equals(id));
    }
}