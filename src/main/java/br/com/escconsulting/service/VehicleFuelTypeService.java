package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeDTO;
import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeSearchDTO;
import br.com.escconsulting.entity.VehicleFuelType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleFuelTypeService {

    Optional<VehicleFuelType> findById(UUID vehicleFuelTypeId);

    List<VehicleFuelType> findAll();

    Page<VehicleFuelTypeDTO> searchPage(LocalUser localUser, VehicleFuelTypeSearchDTO vehicleFuelTypeSearchDTO, Pageable pageable);

    Optional<VehicleFuelType> save(VehicleFuelType vehicleFuelType);

    Optional<VehicleFuelType> update(UUID vehicleFuelTypeId, VehicleFuelType vehicleFuelType);

    void delete(UUID vehicleFuelTypeId);
}