package com.emazon.api_stock.infraestructure.output.jpa.repository;

import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IArticleRepository extends JpaRepository<ArticleEntity, Integer> {

}
