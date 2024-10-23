package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.vehicle.brand.VehicleBrandDTO;
import br.com.escconsulting.dto.vehicle.brand.VehicleBrandSearchDTO;
import br.com.escconsulting.entity.VehicleBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleBrandCustomRepository extends JpaRepository<VehicleBrand, UUID> {

    Page<VehicleBrandDTO> searchPage(VehicleBrandSearchDTO vehicleBrandSearchDTO, Pageable pageable);

    Long countSearchPage(VehicleBrandSearchDTO vehicleBrandSearchDTO);
}