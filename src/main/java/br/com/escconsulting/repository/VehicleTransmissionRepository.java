package br.com.escconsulting.repository;

import br.com.escconsulting.entity.VehicleTransmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleTransmissionRepository extends JpaRepository<VehicleTransmission, UUID> {

}