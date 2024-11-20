package com.nexio.nexio.service;

import com.nexio.nexio.domain.dto.BusinessDto;
import com.nexio.nexio.exceptions.DuplicateResourceException;
import com.nexio.nexio.exceptions.ResourceNotFoundException;
import com.nexio.nexio.repository.BusinessRepository;
import com.nexio.nexio.domain.entity.Business;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessService {
    private final BusinessRepository businessRepository;

    // Constructor para inyectar el repositorio
    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    // Obtener todos los negocios
    public List<BusinessDto> getAllBusiness() {
        return businessRepository.findAll()
                .stream()
                .map(this::mapToDto)// Convertimos cada entidad a DTO
                .collect(Collectors.toList());
    }

    // Creamos un nuevo negocio
    public BusinessDto createBusiness (BusinessDto businessDto) {

        businessRepository.findByName(businessDto.getName()).ifPresent(existingBusiness -> {
            throw new DuplicateResourceException("El nombre del negocio ya está en uso: " + businessDto.getName());
        });

        Business business = mapToEntity(businessDto);  // Convertimos DTO a entidad
        Business savedBusiness = businessRepository.save(business);

        return mapToDto(savedBusiness); // Convertimos entidad a DTO para retornar
    }

    // Obtener negocio por ID
    public BusinessDto getBusinessById (Long id) {
        return businessRepository.findById(id)
                .map(this::mapToDto) // Convertimos entidad a DTO si existe
                .orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado"));
    }

    // Actualizamos un negocio
    public BusinessDto updateBusiness(BusinessDto businessDto) {
        Business existingBusiness = businessRepository.findById(businessDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado"));

        existingBusiness.setName(businessDto.getName());
        existingBusiness.setCategory(businessDto.getCategory());
        existingBusiness.setAddress(businessDto.getAddress());
        existingBusiness.setLatitude(businessDto.getLatitude());
        existingBusiness.setLongitude(businessDto.getLongitude());

        Business updatedBusiness = businessRepository.save(existingBusiness);
        return mapToDto(updatedBusiness);
    }

    // Eliminar un negocio
    public void deleteBusiness(Long id) {
        Business existingBusiness = businessRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Negocio no encontrado"));

        businessRepository.delete(existingBusiness);
    }

    // Método aux: Convertir DTO a entity
    private Business mapToEntity(BusinessDto businessDto) {
        Business business = new Business();
        business.setName(businessDto.getName());
        business.setCategory(businessDto.getCategory());
        business.setAddress(businessDto.getAddress());
        business.setLatitude(businessDto.getLatitude());
        business.setLongitude(businessDto.getLongitude());
        return business;
    }

    // Método aux: Convertir entity a DTO
    private BusinessDto mapToDto(Business business) {
        return new BusinessDto(
                business.getId(),
                business.getName(),
                business.getCategory(),
                business.getAddress(),
                business.getLatitude(),
                business.getLongitude()
        );
    }
}
