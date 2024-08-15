package com.emazon.api_stock.infraestructura.rest.mapeador;

import com.emazon.api_stock.dominio.model.Categoria;
import com.emazon.api_stock.infraestructura.entidad.CategoriaEntidad;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    Categoria categoriaToCategoriaEntidad(CategoriaEntidad categoria);

    Optional<Categoria> categoriaToCategoriaEntidadOptional(Optional<CategoriaEntidad> categoriaEntidad);

    CategoriaEntidad categoriaEntidadToCategoria(Categoria categoria);

    Iterable<Categoria> listCategoriaEntidadToListaCategoria(Iterable<CategoriaEntidad> categoriaEntidad);

}
