package com.emazon.api_stock.infraestructure.output.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idbrand", insertable = false, updatable = false)
    private BrandEntity brand;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "article_x_category",
            joinColumns = @JoinColumn(name = "idarticles"),
            inverseJoinColumns = @JoinColumn(name = "idcategory")
    )
    private List<CategoryEntity> categories;
}
