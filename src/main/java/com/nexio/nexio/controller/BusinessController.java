package com.nexio.nexio.controller;

import com.nexio.nexio.domain.dto.ApiResponseDto;
import com.nexio.nexio.domain.dto.BusinessDto;
import com.nexio.nexio.service.BusinessService;
import com.nexio.nexio.utils.ApiResponseHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    // Obtener todos los negocios
    @GetMapping ResponseEntity<ApiResponseDto<List<BusinessDto>>> getAllBusinesses(HttpServletRequest request) {
        List<BusinessDto> businesses = businessService.getAllBusiness();
        return ApiResponseHelper.success(businesses, "Negocios obtenidos exitosamente", request.getRequestURI());
    }

    // Crear un nuevo negocio
    @PostMapping
    public ResponseEntity<ApiResponseDto<BusinessDto>> createBusinesses(@Valid @RequestBody BusinessDto businessDto, HttpServletRequest request) {
       BusinessDto createBusiness = businessService.createBusiness(businessDto);
       return ApiResponseHelper.success(createBusiness, "Negocio creado exitosamente", request.getRequestURI());
    }

    // Obtener negocio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<BusinessDto>> getBusinessById(@PathVariable Long id, HttpServletRequest request) {
        BusinessDto businessDto = businessService.getBusinessById(id);
        return ApiResponseHelper.success(businessDto,"Negocio encontrado exitosamente", request.getRequestURI());
    }

    // Actualizar negocio existente
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<BusinessDto>> updateBusiness(@PathVariable Long id, @Valid @RequestBody BusinessDto businessDto, HttpServletRequest request) {
        businessDto.setId(id);
        BusinessDto updateBusiness = businessService.updateBusiness(businessDto);
        return ApiResponseHelper.success(updateBusiness, "Negocio actualizado exitosamente", request.getRequestURI());
    }

    // Eliminar negocio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteBusiness(@PathVariable Long id, HttpServletRequest request) {
        businessService.deleteBusiness(id);
        return ApiResponseHelper.success(null, "Negocio eliminado exitosamente" , request.getRequestURI());
    }
}
