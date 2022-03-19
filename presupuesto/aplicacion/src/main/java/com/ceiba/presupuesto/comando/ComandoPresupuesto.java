package com.ceiba.presupuesto.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPresupuesto {

    private Long id;
    private String identificacionUsuario;
    private Long valorPresupuesto;
    private LocalDateTime fechaPresupuesto;
}
