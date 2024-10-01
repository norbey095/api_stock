package com.emazon.api_stock.infraestructure.output.repository;

import com.emazon.api_stock.infraestructure.output.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    @Transactional
    Optional<ArticleEntity> findByName(String name);

    @Transactional
    @Query("SELECT a FROM ArticleEntity a " +
            "JOIN FETCH a.brand b " +
            "JOIN FETCH a.categories c " +
            "WHERE (:brandName IS NULL OR b.name = :brandName) " +
            "AND (:categoryName IS NULL OR c.name = :categoryName) " +
            "AND (:ids IS NULL OR a.id IN :ids)")
    Page<ArticleEntity> findAllItemsByBrandName(@Param("brandName") String brandName,
                                                @Param("categoryName") String categoryName,
                                                @Param("ids") List<Integer> ids,
                                                Pageable pageable);

    @Transactional
    @Query("SELECT a FROM ArticleEntity a " +
            "WHERE (:ids IS NULL OR a.id IN :ids)")
    List<ArticleEntity> findPricesByIds(@Param("ids") List<Integer> ids);

    @Transactional
    @Modifying
    @Query("UPDATE ArticleEntity a SET a.quantity = :quantity WHERE a.id = :articleId")
    void updateQuantityById(@Param("articleId")Integer articleId,@Param("quantity") Integer quantity);
}
