package com.vaxi.spring_boot_microservice_1_inmueble.service;

import java.util.List;

import com.vaxi.spring_boot_microservice_1_inmueble.model.Inmueble;

public interface InmuebleService {

    Inmueble saveInmueble(Inmueble inmueble);

    void deleteInmueble(Long inmuebleId);

    List<Inmueble> findAllInmuebles();
}