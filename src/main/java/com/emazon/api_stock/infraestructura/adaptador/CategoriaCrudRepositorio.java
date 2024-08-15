package com.emazon.api_stock.infraestructura.adaptador;

import com.emazon.api_stock.dominio.model.Categoria;
import com.emazon.api_stock.dominio.puerto.CategoriaRepositorio;
import com.emazon.api_stock.infraestructura.entidad.CategoriaEntidad;
import com.emazon.api_stock.infraestructura.rest.mapeador.CategoriaMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
@AllArgsConstructor
public class CategoriaCrudRepositorio implements CategoriaRepositorio {//no debería llamar al servicio de aplicacion?

    private final CategoriaCrudRepositorioMysql categoriaRepositorio;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        CategoriaEntidad categoriaEntidad = this.categoriaMapper.categoriaEntidadToCategoria(categoria);
        return this.categoriaMapper.categoriaToCategoriaEntidad(this.categoriaRepositorio.save(categoriaEntidad));
    }

    @Override
    public Optional<Categoria> obtenerCategoria(Integer id) {
        return this.categoriaMapper.categoriaToCategoriaEntidadOptional(this.categoriaRepositorio.findById(id));
    }

    @Override
    public Iterable<Categoria> obtenerCategorias() {
        return this.categoriaMapper.listCategoriaEntidadToListaCategoria(this.categoriaRepositorio.findAll());
    }

    @Override
    public void eliminarCategoria(Integer id) {
        this.categoriaRepositorio.deleteById(id);
    }
}
