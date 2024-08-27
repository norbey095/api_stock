package com.emazon.api_stock.infraestructure.output.jpa.repository;

import com.emazon.api_stock.infraestructure.output.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Integer> {

    Optional<BrandEntity> findByName(String name);

}
