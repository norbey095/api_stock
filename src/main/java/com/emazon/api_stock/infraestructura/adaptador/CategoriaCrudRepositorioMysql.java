package com.emazon.api_stock.infraestructura.adaptador;

import com.emazon.api_stock.infraestructura.entidad.CategoriaEntidad;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaCrudRepositorioMysql extends CrudRepository<CategoriaEntidad,Integer> {
}
