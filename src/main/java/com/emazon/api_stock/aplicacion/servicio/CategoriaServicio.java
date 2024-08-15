package com.emazon.api_stock.aplicacion.servicio;

import com.emazon.api_stock.dominio.model.Categoria;

import java.util.Optional;

public interface CategoriaServicio {

    Categoria guardarCategoria(Categoria categoria);
    Optional<Categoria> obtenerCategoria(Integer id);
    Iterable<Categoria> obtenerCategorias();
    void eliminarCategoria(Integer id);
}
