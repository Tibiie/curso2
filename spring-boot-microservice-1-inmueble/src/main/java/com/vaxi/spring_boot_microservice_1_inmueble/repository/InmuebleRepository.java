package com.vaxi.spring_boot_microservice_1_inmueble.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vaxi.spring_boot_microservice_1_inmueble.model.Inmueble;

@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble, Long>{

}
