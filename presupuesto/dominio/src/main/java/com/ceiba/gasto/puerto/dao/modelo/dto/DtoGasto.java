package com.ceiba.gasto.puerto.dao.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoGasto {
    private Long id;
    private String identificacionUsuario;
    private int valorGasto;
    private LocalDateTime fechaGasto;
}
