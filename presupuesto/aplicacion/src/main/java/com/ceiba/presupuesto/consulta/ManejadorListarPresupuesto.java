package com.ceiba.presupuesto.consulta;


import com.ceiba.presupuesto.modelo.dto.DtoPresupuesto;
import com.ceiba.presupuesto.puerto.dao.DaoPresupuesto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarPresupuesto {

    private final DaoPresupuesto daoPresupuesto;

    public ManejadorListarPresupuesto(DaoPresupuesto daoPresupuesto){
        this.daoPresupuesto = daoPresupuesto;
    }

    public List<DtoPresupuesto> ejecutar(){ return this.daoPresupuesto.listar(); }
}
