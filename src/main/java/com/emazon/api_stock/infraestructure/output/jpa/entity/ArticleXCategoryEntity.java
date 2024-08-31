package com.emazon.api_stock.infraestructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "article_x_category")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleXCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idcategory;
    private Integer idarticles;
}
