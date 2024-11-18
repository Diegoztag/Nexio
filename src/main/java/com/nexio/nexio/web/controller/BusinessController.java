package com.nexio.nexio.web.controller;

import com.nexio.nexio.domain.dto.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nexio.nexio.domain.repository.BusinessRepository;

import java.util.List;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    // Crear nuevo negocio
    @PostMapping
    public Business createBusiness(@RequestBody Business business) {
        return businessRepository.save(business);
    }

    // Obtener todos los negocios
    @GetMapping
    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    // Obtener negocio por ID
    @GetMapping("/{id}")
    public Business getBusinessById(@PathVariable Long id) {
        return businessRepository.findById(id).orElseThrow(() -> new RuntimeException("Negocio no encontrado"));
    }
}
