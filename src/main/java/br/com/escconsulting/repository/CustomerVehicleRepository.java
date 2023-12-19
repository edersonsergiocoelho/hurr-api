package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerVehicleRepository extends JpaRepository<CustomerVehicle, UUID> {

}