package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleDTO;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleSaveDTO;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleSearchDTO;
import br.com.escconsulting.entity.CustomerVehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleService {

    Optional<CustomerVehicle> findById(UUID customerVehicleId);

    boolean existsByCode(String code);

    List<CustomerVehicle> findAll();

    Page<CustomerVehicleDTO> searchPage(CustomerVehicleSearchDTO customerVehicleSearchDTO, Pageable pageable);

    Optional<CustomerVehicle> save(LocalUser localUser, CustomerVehicleSaveDTO customerVehicleSaveDTO);

    Optional<CustomerVehicle> update(UUID customerVehicleId, CustomerVehicle customerVehicle);

    void delete(UUID customerVehicleId);
}