package com.ceiba.presupuesto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoPresupuesto {
    private Long id;
    private String identificacionUsuario;
    private Long valorPresupusto;
    private LocalDateTime fechaPresupuesto;
}
