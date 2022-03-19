package com.ceiba.configuracion;

import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;
import com.ceiba.gasto.servicio.ServicioActualizarGasto;
import com.ceiba.gasto.servicio.ServicioCrearGasto;
import com.ceiba.gasto.servicio.ServicioEliminarGasto;
import com.ceiba.presupuesto.puerto.repositorio.RepositorioPresupuesto;
import com.ceiba.presupuesto.servicio.ServicioActualizarPresupuesto;
import com.ceiba.presupuesto.servicio.ServicioCrearPresupuesto;
import com.ceiba.presupuesto.servicio.ServicioEliminarPresupuesto;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioCrearGasto servicioCrearGasto(RepositorioGasto repositorioGasto) {
        return new ServicioCrearGasto(repositorioGasto);
    }

    @Bean
    public ServicioEliminarGasto servicioEliminarGasto(RepositorioGasto repositorioGasto) {
        return new ServicioEliminarGasto(repositorioGasto);
    }

    @Bean
    public ServicioActualizarGasto servicioActualizarGasto(RepositorioGasto repositorioGasto) {
        return new ServicioActualizarGasto(repositorioGasto);
    }

    @Bean
    public ServicioCrearPresupuesto servicioCrearPresupuesto(RepositorioPresupuesto repositorioPresupuesto) {
        return new ServicioCrearPresupuesto(repositorioPresupuesto);
    }

    @Bean
    public ServicioEliminarPresupuesto servicioEliminarPresupuesto(RepositorioPresupuesto repositorioPresupuesto) {
        return new ServicioEliminarPresupuesto(repositorioPresupuesto);
    }

    @Bean
    public ServicioActualizarPresupuesto servicioActualizarPresupuesto(RepositorioPresupuesto repositorioPresupuesto) {
        return new ServicioActualizarPresupuesto(repositorioPresupuesto);
    }
	

}
