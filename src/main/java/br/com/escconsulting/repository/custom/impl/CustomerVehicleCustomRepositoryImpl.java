package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleDTO;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleSearchDTO;
import br.com.escconsulting.entity.CustomerVehicle;
import br.com.escconsulting.mapper.CustomerVehicleMapper;
import br.com.escconsulting.repository.CustomerVehicleRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleCustomRepository;
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
public class CustomerVehicleCustomRepositoryImpl extends SimpleJpaRepository<CustomerVehicle, UUID> implements CustomerVehicleCustomRepository {

    private final EntityManager entityManager;

    private final CustomerVehicleRepository customerVehicleRepository;

    public CustomerVehicleCustomRepositoryImpl(EntityManager entityManager, CustomerVehicleRepository customerVehicleRepository) {
        super(CustomerVehicle.class, entityManager);
        this.entityManager = entityManager;
        this.customerVehicleRepository = customerVehicleRepository;
    }

    public Page<CustomerVehicleDTO> searchPage(CustomerVehicleSearchDTO customerVehicleSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicle> cq = cb.createQuery(CustomerVehicle.class);
        Root<CustomerVehicle> root = cq.from(CustomerVehicle.class);

        root.fetch("customer", JoinType.LEFT);
        root.fetch("vehicle", JoinType.LEFT);
        root.fetch("vehicleModel", JoinType.LEFT);
        root.fetch("vehicleColor", JoinType.LEFT);
        root.fetch("vehicleFuelType", JoinType.LEFT);
        root.fetch("vehicleTransmission", JoinType.LEFT);
        root.fetch("addresses", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        if (customerVehicleSearchDTO != null) {
            if (customerVehicleSearchDTO.getCustomerId() != null) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), customerVehicleSearchDTO.getCustomerId()));
            }

            /*
            if (customerVehicleSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerVehicleSearchDTO.getPaymentMethodId()));
            }

            if (customerVehicleSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerVehicleSearchDTO.getPaymentStatusId()));
            }
            */
        }

        cq.where(spec);

        if (pageable.getSort().isSorted()) {
            List<Order> orders = pageable.getSort().stream()
                    .map(order -> buildOrder(cb, root, order))
                    .collect(Collectors.toList());
            cq.orderBy(orders);
        }

        TypedQuery<CustomerVehicle> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerVehicle> resultList = query.getResultList();

        List<CustomerVehicleDTO> dtoList = resultList.stream()
                .map(CustomerVehicleMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerVehicleSearchDTO));
    }

    public Long countSearchPage(CustomerVehicleSearchDTO customerVehicleSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerVehicle> root = cq.from(CustomerVehicle.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerVehicleSearchDTO != null) {
            if (customerVehicleSearchDTO.getCustomerId() != null) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), customerVehicleSearchDTO.getCustomerId()));
            }

            /*
            if (customerVehicleSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerVehicleSearchDTO.getPaymentMethodId()));
            }

            if (customerVehicleSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerVehicleSearchDTO.getPaymentStatusId()));
            }
            */
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<CustomerVehicle> root, Sort.Order order) {
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