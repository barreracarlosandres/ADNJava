package com.ceiba.gasto.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoGasto {

    private Long id;
    private String identificacionUsuario;
    private int valorGasto;
    private LocalDateTime fechaGasto;
}
