package br.com.escconsulting.service;

import br.com.escconsulting.dto.vehicle.brand.VehicleBrandDTO;
import br.com.escconsulting.dto.vehicle.brand.VehicleBrandSearchDTO;
import br.com.escconsulting.entity.VehicleBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleBrandService {

    Optional<VehicleBrand> findById(UUID vehicleBrandId);

    Optional<VehicleBrand> findByVehicleBrandName(String vehicleBrandName);

    List<VehicleBrand> findAll();

    Page<VehicleBrandDTO> searchPage(VehicleBrandSearchDTO vehicleBrandSearchDTO, Pageable pageable);

    Optional<VehicleBrand> save(VehicleBrand vehicleBrand);

    Optional<VehicleBrand> update(UUID vehicleBrandId, VehicleBrand vehicleBrand);

    void delete(UUID vehicleBrandId);

    void deleteAll(List<UUID> vehicleBrandIds);
}