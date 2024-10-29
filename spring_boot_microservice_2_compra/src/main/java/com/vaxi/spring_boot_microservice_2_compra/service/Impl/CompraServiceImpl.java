package com.vaxi.spring_boot_microservice_2_compra.service.Impl;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaxi.spring_boot_microservice_2_compra.model.Compra;
import com.vaxi.spring_boot_microservice_2_compra.service.CompraService;
import com.vaxi.spring_boot_microservice_2_compra.repository.CompraRepository;
@Service
public class CompraServiceImpl implements CompraService{

    @Autowired
    private CompraRepository compraRepository;

    @Override
    public Compra saveCompra(Compra compra){
        compra.setFechaCompra(LocalDateTime.now());
        return compraRepository.save(compra);
    }

    @Override
    public List<Compra> findAllComprasOfUser(Long userId){
        return compraRepository.findAllByUserId(userId);
    }
}
