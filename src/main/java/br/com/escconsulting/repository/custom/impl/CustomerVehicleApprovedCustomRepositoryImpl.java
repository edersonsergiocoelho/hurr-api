package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedDTO;
import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedSearchDTO;
import br.com.escconsulting.entity.*;
import br.com.escconsulting.mapper.CustomerVehicleApprovedMapper;
import br.com.escconsulting.repository.CustomerVehicleApprovedRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleApprovedCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CustomerVehicleApprovedCustomRepositoryImpl extends SimpleJpaRepository<CustomerVehicleApproved, UUID> implements CustomerVehicleApprovedCustomRepository {

    private final EntityManager entityManager;

    private final CustomerVehicleApprovedRepository customerVehicleApprovedRepository;

    public CustomerVehicleApprovedCustomRepositoryImpl(EntityManager entityManager, CustomerVehicleApprovedRepository customerVehicleApprovedRepository) {
        super(CustomerVehicleApproved.class, entityManager);
        this.entityManager = entityManager;
        this.customerVehicleApprovedRepository = customerVehicleApprovedRepository;
    }

    public Page<CustomerVehicleApprovedDTO> searchPage(CustomerVehicleApprovedSearchDTO customerVehicleApprovedSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleApproved> cq = cb.createQuery(CustomerVehicleApproved.class);
        Root<CustomerVehicleApproved> root = cq.from(CustomerVehicleApproved.class);

        Fetch<CustomerVehicleApproved, CustomerVehicle> customerVehicleFetch = root.fetch("customerVehicle", JoinType.LEFT);
        customerVehicleFetch.fetch("customer", JoinType.LEFT);

        Fetch<CustomerVehicle, Vehicle> customerVehicleVehicleFetch = customerVehicleFetch.fetch("vehicle", JoinType.LEFT);
        customerVehicleVehicleFetch.fetch("vehicleBrand", JoinType.LEFT);

        customerVehicleFetch.fetch("vehicleModel", JoinType.LEFT).fetch("vehicleCategory", JoinType.LEFT);
        customerVehicleFetch.fetch("vehicleColor", JoinType.LEFT);
        customerVehicleFetch.fetch("vehicleFuelType", JoinType.LEFT);
        customerVehicleFetch.fetch("vehicleTransmission", JoinType.LEFT);

        Fetch<CustomerVehicle, CustomerVehicleAddress> customerVehicleCustomerVehicleAddressFetch = customerVehicleFetch.fetch("addresses", JoinType.LEFT);
        Fetch<CustomerVehicleAddress, Address> customerVehicleAddressAddressFetch = customerVehicleCustomerVehicleAddressFetch.fetch("address", JoinType.LEFT);
        customerVehicleAddressAddressFetch.fetch("country", JoinType.LEFT);
        customerVehicleAddressAddressFetch.fetch("state", JoinType.LEFT);
        customerVehicleAddressAddressFetch.fetch("city", JoinType.LEFT);

        Predicate spec = buildSearchPredicates(customerVehicleApprovedSearchDTO, cb, root);

        cq.where(spec);

        if (pageable.getSort().isSorted()) {
            List<Order> orders = pageable.getSort().stream()
                    .map(order -> buildOrder(cb, root, order))
                    .collect(Collectors.toList());
            cq.orderBy(orders);
        }

        TypedQuery<CustomerVehicleApproved> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerVehicleApproved> resultList = query.getResultList();

        List<CustomerVehicleApprovedDTO> dtoList = resultList.stream()
                .map(CustomerVehicleApprovedMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerVehicleApprovedSearchDTO));
    }

    public Long countSearchPage(CustomerVehicleApprovedSearchDTO customerVehicleApprovedSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerVehicleApproved> root = cq.from(CustomerVehicleApproved.class);
        cq.select(cb.count(root));

        Predicate spec = buildSearchPredicates(customerVehicleApprovedSearchDTO, cb, root);

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Predicate buildSearchPredicates(CustomerVehicleApprovedSearchDTO searchDTO, CriteriaBuilder cb, Root<CustomerVehicleApproved> root) {

        Predicate spec = cb.conjunction();

        if (searchDTO != null) {

            if (searchDTO.getVehicleBrandId() != null) {
                spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("vehicle").get("vehicleBrand").get("vehicleBrandId"), searchDTO.getVehicleBrandId()));
            }

            if (searchDTO.getVehicleId() != null) {
                spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("vehicle").get("vehicleId"), searchDTO.getVehicleId()));
            }

            if (searchDTO.getVehicleModelId() != null) {
                spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("vehicleModel").get("vehicleModelId"), searchDTO.getVehicleModelId()));
            }

            if (searchDTO.getFirstName() != null && !searchDTO.getFirstName().isEmpty()) {
                spec = cb.and(spec, cb.like(cb.upper(root.get("customerVehicle").get("customer").get("firstName")), "%" + searchDTO.getFirstName().toUpperCase() + "%"));
            }

            if (searchDTO.getLastName() != null && !searchDTO.getLastName().isEmpty()) {
                spec = cb.and(spec, cb.like(cb.upper(root.get("customerVehicle").get("customer").get("lastName")), "%" + searchDTO.getLastName().toUpperCase() + "%"));
            }

            if (searchDTO.getCpf() != null && ! searchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customer").get("cpf"), searchDTO.getCpf()));
            }
        }

        return spec;
    }

    private Order buildOrder(CriteriaBuilder cb, Root<CustomerVehicleApproved> root, Sort.Order order) {
        String property = order.getProperty();
        Path<Object> path = getPath(root, property);
        return order.isAscending() ? cb.asc(path) : cb.desc(path);
    }

    private Path<Object> getPath(From<?, ?> root, String property) {
        String[] properties = property.split("\\.");
        Path<Object> path = (Path<Object>) root;
        for (String prop : properties) {
            path = path.get(prop);
        }
        return path;
    }
}