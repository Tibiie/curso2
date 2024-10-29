package com.vaxi.spring_boot_microservice_1_inmueble.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaxi.spring_boot_microservice_1_inmueble.model.Inmueble;
import com.vaxi.spring_boot_microservice_1_inmueble.service.InmuebleService;

@RestController
@RequestMapping("api/inmueble")
public class InmuebleController {

    //INYECTAMOS EL SERVICIO GENERANDO UN OBJETO
    @Autowired
    private InmuebleService inmuebleService;

     //obtener los inmuebles
     @GetMapping
     public ResponseEntity<?> getAllInmuebles(){
        return ResponseEntity.ok(inmuebleService.findAllInmuebles());
    }

    //EL ResponseEntity es un objeto que contiene el estado de la operacion
    @PostMapping("/crearInmueble")
    public ResponseEntity<?> saveInmueble(@RequestBody Inmueble inmueble){
        //Se envia el resultado de la operacion
        return new ResponseEntity<>(inmuebleService.saveInmueble(inmueble), HttpStatus.CREATED);
    }

    //Eliminar un inmueble
    @DeleteMapping("/eliminarInmueble/{inmuebleId}")
    public ResponseEntity<?> deleteInmueble(@PathVariable Long inmuebleId){
        inmuebleService.deleteInmueble(inmuebleId);
        return new ResponseEntity<>(HttpStatus.OK);
    } 
}
