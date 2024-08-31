package com.emazon.api_stock.infraestructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private double price;
    private Integer idbrand;
}
