package com.emazon.api_stock.infraestructura.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categoria")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50, message = "El nombre no puede tener mas de 50 caracteres")
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @Size(max = 90, message = "La descripción no puede tener mas de 90 caracteres")
    @NotBlank(message = "La descripción no puede estar vacio")
    private String descripcion;


}
