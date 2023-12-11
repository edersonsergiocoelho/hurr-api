package br.com.escconsulting.service;

import br.com.escconsulting.dto.customer.vehicle.SearchCustomerVehicle;
import br.com.escconsulting.model.*;
import br.com.escconsulting.repository.CustomerVehicleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerVehicleService {

    @Autowired
    private CustomerVehicleRepository customerVehicleRepository;

    @Autowired
    private EntityManager entityManager;

    public List<CustomerVehicle> findAll() {
        return customerVehicleRepository.findAll();
    }

    public CustomerVehicle findById(UUID id) {
        return customerVehicleRepository.findById(id).orElseThrow();
    }

    public List<CustomerVehicle> search(SearchCustomerVehicle searchCustomerVehicle) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicle> cq = cb.createQuery(CustomerVehicle.class);
        Root<CustomerVehicle> root = cq.from(CustomerVehicle.class);

        Fetch<CustomerVehicle, Vehicle> vehicleFetch = root.fetch("vehicle");
        Fetch<Vehicle, VehicleBrand> vehicleBrandFetch = vehicleFetch.fetch("vehicleBrand");

        Fetch<CustomerVehicle, VehicleModel> vehicleModelFetch = root.fetch("vehicleModel");
        Fetch<VehicleModel, VehicleCategory> vehicleCategoryFetch = vehicleModelFetch.fetch("vehicleCategory");

        root.fetch("vehicleColor");
        root.fetch("vehicleFuelType");

        Predicate spec = cb.conjunction();

        if (searchCustomerVehicle.getVehicleId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicle").get("vehicleId"), searchCustomerVehicle.getVehicleId()));
        }

        if (searchCustomerVehicle.getVehicleModelId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleModel").get("vehicleModelId"), searchCustomerVehicle.getVehicleModelId()));
        }

        if (searchCustomerVehicle.getVehicleBrandId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicle").get("vehicleBrand").get("vehicleBrandId"), searchCustomerVehicle.getVehicleBrandId()));
        }

        if (searchCustomerVehicle.getVehicleCategoryId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleModel").get("vehicleCategory").get("vehicleCategoryId"), searchCustomerVehicle.getVehicleCategoryId()));
        }

        if (searchCustomerVehicle.getVehicleColorId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleColor").get("vehicleColorId"), searchCustomerVehicle.getVehicleColorId()));
        }

        if (searchCustomerVehicle.getVehicleFuelTypeId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleFuelType").get("vehicleFuelTypeId"), searchCustomerVehicle.getVehicleFuelTypeId()));
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getResultList();
    }

    public void save(CustomerVehicle customerVehicle) {
        customerVehicleRepository.save(customerVehicle);
    }

    public void deleteById(UUID id) {
        customerVehicleRepository.deleteById(id);
    }
}