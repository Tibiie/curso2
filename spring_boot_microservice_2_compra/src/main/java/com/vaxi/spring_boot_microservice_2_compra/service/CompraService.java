package com.vaxi.spring_boot_microservice_2_compra.service;
import java.util.List;
import com.vaxi.spring_boot_microservice_2_compra.model.Compra;

public interface CompraService {

     public Compra saveCompra(Compra compra);

     public List<Compra> findAllComprasOfUser(Long userId);
}
