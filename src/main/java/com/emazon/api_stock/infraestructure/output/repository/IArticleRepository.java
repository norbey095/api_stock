package com.emazon.api_stock.infraestructure.output.repository;

import com.emazon.api_stock.infraestructure.output.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    Optional<ArticleEntity> findByName(String name);

   @Query("SELECT a FROM ArticleEntity a " +
           "JOIN FETCH a.brand b " +
           "JOIN FETCH a.categories c ")
    Page<ArticleEntity> findAllItemsByBrandName(Pageable pageable);

}
