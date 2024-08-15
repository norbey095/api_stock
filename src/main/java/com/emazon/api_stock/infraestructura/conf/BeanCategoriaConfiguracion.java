package com.emazon.api_stock.infraestructura.conf;

import com.emazon.api_stock.aplicacion.servicio.CategoriaServicio;
import com.emazon.api_stock.aplicacion.servicio.DominioCategoriaServicio;
import com.emazon.api_stock.dominio.puerto.CategoriaRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanCategoriaConfiguracion {

    @Bean
    CategoriaServicio productBeanService(final CategoriaRepositorio categoriaRepositorio){
        return new DominioCategoriaServicio(categoriaRepositorio);
    }
}
