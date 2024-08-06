package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressDTO;
import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressSearchDTO;
import br.com.escconsulting.entity.Address;
import br.com.escconsulting.entity.CustomerVehicleAddress;
import br.com.escconsulting.mapper.CustomerVehicleAddressMapper;
import br.com.escconsulting.repository.CustomerVehicleAddressRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleAddressCustomRepository;
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
public class CustomerVehicleAddressCustomRepositoryImpl extends SimpleJpaRepository<CustomerVehicleAddress, UUID> implements CustomerVehicleAddressCustomRepository {

    private final EntityManager entityManager;

    private final CustomerVehicleAddressRepository customerVehicleAddressRepository;

    public CustomerVehicleAddressCustomRepositoryImpl(EntityManager entityManager, CustomerVehicleAddressRepository customerVehicleAddressRepository) {
        super(CustomerVehicleAddress.class, entityManager);
        this.entityManager = entityManager;
        this.customerVehicleAddressRepository = customerVehicleAddressRepository;
    }

    public Page<CustomerVehicleAddressDTO> searchPage(CustomerVehicleAddressSearchDTO customerVehicleAddressSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleAddress> cq = cb.createQuery(CustomerVehicleAddress.class);
        Root<CustomerVehicleAddress> root = cq.from(CustomerVehicleAddress.class);

        Fetch<CustomerVehicleAddress, Address> addressFetch = root.fetch("address", JoinType.LEFT);
        addressFetch.fetch("country", JoinType.LEFT);
        addressFetch.fetch("city", JoinType.LEFT);
        addressFetch.fetch("state", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        if (customerVehicleAddressSearchDTO != null) {
            if (customerVehicleAddressSearchDTO.getCustomerVehicleId() != null) {
                spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customerVehicleId"), customerVehicleAddressSearchDTO.getCustomerVehicleId()));
            }

            if (customerVehicleAddressSearchDTO.getNickname() != null && !customerVehicleAddressSearchDTO.getNickname().isEmpty()) {
                spec = cb.and(spec, cb.like(cb.upper(root.get("address").get("nickname")), "%" + customerVehicleAddressSearchDTO.getNickname().toUpperCase() + "%"));
            }

            if (customerVehicleAddressSearchDTO.getEnabled() != null) {
                spec = cb.and(spec, cb.equal(root.get("enabled"), customerVehicleAddressSearchDTO.getEnabled()));
            }
        }

        cq.where(spec);

        if (pageable.getSort().isSorted()) {
            List<Order> orders = pageable.getSort().stream()
                    .map(order -> buildOrder(cb, root, order))
                    .collect(Collectors.toList());
            cq.orderBy(orders);
        }

        TypedQuery<CustomerVehicleAddress> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerVehicleAddress> resultList = query.getResultList();

        List<CustomerVehicleAddressDTO> dtoList = resultList.stream()
                .map(CustomerVehicleAddressMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerVehicleAddressSearchDTO));
    }

    public Long countSearchPage(CustomerVehicleAddressSearchDTO customerVehicleAddressSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerVehicleAddress> root = cq.from(CustomerVehicleAddress.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerVehicleAddressSearchDTO != null) {
            if (customerVehicleAddressSearchDTO.getCustomerVehicleId() != null) {
                spec = cb.and(spec, cb.equal(root.get("customerVehicle").get("customerVehicleId"), customerVehicleAddressSearchDTO.getCustomerVehicleId()));
            }

            if (customerVehicleAddressSearchDTO.getNickname() != null && !customerVehicleAddressSearchDTO.getNickname().isEmpty()) {
                spec = cb.and(spec, cb.like(cb.upper(root.get("address").get("nickname")), "%" + customerVehicleAddressSearchDTO.getNickname().toUpperCase() + "%"));
            }

            if (customerVehicleAddressSearchDTO.getEnabled() != null) {
                spec = cb.and(spec, cb.equal(root.get("enabled"), customerVehicleAddressSearchDTO.getEnabled()));
            }
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<CustomerVehicleAddress> root, Sort.Order order) {
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