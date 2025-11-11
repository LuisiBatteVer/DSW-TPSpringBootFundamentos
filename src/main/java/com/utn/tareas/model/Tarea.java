package com.utn.tareas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Incluye @Getter, @Setter, @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {

    private Long id;
    private String descripcion;
    private boolean completada = false;
    private Prioridad prioridad;
}
