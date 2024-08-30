package com.emazon.api_stock.infraestructure.output.jpa.repository;

import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleXCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IArticleXCategoryRepository extends JpaRepository<ArticleXCategoryEntity, Integer> {

}
