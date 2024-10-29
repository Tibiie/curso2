package com.vaxi.spring_boot_microservice_2_compra.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaxi.spring_boot_microservice_2_compra.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{
    List<Compra> findAllByUserId(Long userId);
}