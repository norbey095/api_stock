package com.emazon.api_stock.infraestructure.output.jpa.repository;

import com.emazon.api_stock.infraestructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Integer> {

   @Query("SELECT a FROM ArticleEntity a " +
           "JOIN FETCH a.brand b " +
           "JOIN FETCH a.categories c ")
    Page<ArticleEntity> findAllItemsByBrandName(Pageable pageable);

}
