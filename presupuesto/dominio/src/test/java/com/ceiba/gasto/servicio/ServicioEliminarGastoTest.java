package com.ceiba.gasto.servicio;

import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioEliminarGastoTest {

    @Test
    @DisplayName("Deberia eliminar el gasto llamando al repositorio")
    void deberiaEliminarElGastoLlamandoAlRepositorio() {
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        ServicioEliminarGasto servicioEliminarGasto = new ServicioEliminarGasto(repositorioGasto);

        servicioEliminarGasto.ejecutar(1l);

        Mockito.verify(repositorioGasto, Mockito.times(1)).eliminar(1l);

    }

}
