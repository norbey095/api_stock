package com.emazon.api_stock.infraestructura.rest.controlador;


import com.emazon.api_stock.aplicacion.servicio.CategoriaServicio;
import com.emazon.api_stock.dominio.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
@Slf4j
@AllArgsConstructor
public class CategoriaControlador {

    private final CategoriaServicio categoriaServicio;

    @GetMapping("/obtenerCategorias")
    public ResponseEntity<Iterable<Categoria>> obtenerCategorias(){
        return new ResponseEntity<>(this.categoriaServicio.obtenerCategorias(), HttpStatus.OK);
    }

    @PostMapping("/guardarCategoria")
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria){
        log.info("Categoria: {}",categoria);
        return new ResponseEntity<>(this.categoriaServicio.guardarCategoria(categoria), HttpStatus.CREATED);
    }

    @GetMapping("/obtenerCategoria/{id}")
    public ResponseEntity<Optional<Categoria>> obtenerCategoria(@PathVariable Integer id){
        return new ResponseEntity<>(this.categoriaServicio.obtenerCategoria(id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminarCategoria/{id}")
    public void eliminarCategoria(@PathVariable Integer id){
        this.categoriaServicio.eliminarCategoria(id);
    }

}
