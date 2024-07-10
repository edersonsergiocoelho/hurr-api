package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.vehicle.color.VehicleColorDTO;
import br.com.escconsulting.dto.vehicle.color.VehicleColorSearchDTO;
import br.com.escconsulting.entity.VehicleColor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleColorService {

    Optional<VehicleColor> findById(UUID vehicleColorId);

    List<VehicleColor> findAll();

    Page<VehicleColorDTO> searchPage(LocalUser localUser, VehicleColorSearchDTO vehicleColorSearchDTO, Pageable pageable);

    Optional<VehicleColor> save(VehicleColor vehicleColor);

    Optional<VehicleColor> update(UUID vehicleColorId, VehicleColor vehicleColor);

    void delete(UUID vehicleColorId);
}