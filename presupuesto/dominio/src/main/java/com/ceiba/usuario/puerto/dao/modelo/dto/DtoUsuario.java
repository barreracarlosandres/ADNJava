package com.ceiba.usuario.puerto.dao.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoUsuario {
    private Long id;
    private String nombre;
    private String clave;
    private LocalDateTime fechaCreacion;

}
