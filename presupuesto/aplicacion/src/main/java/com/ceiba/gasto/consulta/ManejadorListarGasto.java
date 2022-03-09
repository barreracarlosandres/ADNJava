package com.ceiba.gasto.consulta;


import com.ceiba.gasto.modelo.dto.DtoGasto;
import com.ceiba.gasto.puerto.dao.DaoGasto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarGasto {

    private final DaoGasto daoGasto;

    public ManejadorListarGasto(DaoGasto daoGasto){
        this.daoGasto = daoGasto;
    }

    public List<DtoGasto> ejecutar(){ return this.daoGasto.listar(); }
}
