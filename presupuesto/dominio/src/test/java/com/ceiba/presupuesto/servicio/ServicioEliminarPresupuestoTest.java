package com.ceiba.presupuesto.servicio;

import com.ceiba.presupuesto.puerto.repositorio.RepositorioPresupuesto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioEliminarPresupuestoTest {

    @Test
    @DisplayName("Deberia eliminar el presupuesto llamando al repositorio")
    void deberiaEliminarElPresupuestoLlamandoAlRepositorio() {
        RepositorioPresupuesto repositorioPresupuesto = Mockito.mock(RepositorioPresupuesto.class);
        ServicioEliminarPresupuesto servicioEliminarPresupuesto = new ServicioEliminarPresupuesto(repositorioPresupuesto);

        servicioEliminarPresupuesto.ejecutar(1l);

        Mockito.verify(repositorioPresupuesto, Mockito.times(1)).eliminar(1l);

    }

}
