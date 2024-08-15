package com.emazon.api_stock.aplicacion.servicio;

import com.emazon.api_stock.dominio.model.Categoria;
import com.emazon.api_stock.dominio.puerto.CategoriaRepositorio;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DominioCategoriaServicio implements CategoriaServicio {

    private final CategoriaRepositorio categoriaRepositorio;

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return this.categoriaRepositorio.guardarCategoria(categoria);
    }

    @Override
    public Optional<Categoria> obtenerCategoria(Integer id) {
        return this.categoriaRepositorio.obtenerCategoria(id);
    }

    @Override
    public Iterable<Categoria> obtenerCategorias() {
        return this.categoriaRepositorio.obtenerCategorias();
    }

    @Override
    public void eliminarCategoria(Integer id) {
        this.categoriaRepositorio.eliminarCategoria(id);
    }
}
