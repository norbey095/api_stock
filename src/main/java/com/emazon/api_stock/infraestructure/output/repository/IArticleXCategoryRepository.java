package com.emazon.api_stock.infraestructure.output.repository;

import com.emazon.api_stock.infraestructure.output.entity.ArticleXCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleXCategoryRepository extends JpaRepository<ArticleXCategoryEntity, Integer> {

}
