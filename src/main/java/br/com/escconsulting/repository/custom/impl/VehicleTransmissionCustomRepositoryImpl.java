package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionDTO;
import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionSearchDTO;
import br.com.escconsulting.entity.VehicleTransmission;
import br.com.escconsulting.mapper.VehicleTransmissionMapper;
import br.com.escconsulting.repository.VehicleTransmissionRepository;
import br.com.escconsulting.repository.custom.VehicleTransmissionCustomRepository;
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
public class VehicleTransmissionCustomRepositoryImpl extends SimpleJpaRepository<VehicleTransmission, UUID> implements VehicleTransmissionCustomRepository {

    private final EntityManager entityManager;

    private final VehicleTransmissionRepository vehicleTransmissionRepository;

    public VehicleTransmissionCustomRepositoryImpl(EntityManager entityManager, VehicleTransmissionRepository vehicleTransmissionRepository) {
        super(VehicleTransmission.class, entityManager);
        this.entityManager = entityManager;
        this.vehicleTransmissionRepository = vehicleTransmissionRepository;
    }

    public Page<VehicleTransmissionDTO> searchPage(VehicleTransmissionSearchDTO vehicleTransmissionSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VehicleTransmission> cq = cb.createQuery(VehicleTransmission.class);
        Root<VehicleTransmission> root = cq.from(VehicleTransmission.class);

        root.fetch("customer", JoinType.LEFT);
        root.fetch("vehicle", JoinType.LEFT);
        root.fetch("vehicleModel", JoinType.LEFT);
        root.fetch("vehicleColor", JoinType.LEFT);
        root.fetch("vehicleFuelType", JoinType.LEFT);
        root.fetch("vehicleTransmission", JoinType.LEFT);
        root.fetch("addresses", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        if (vehicleTransmissionSearchDTO != null) {
            /*
            if (vehicleTransmissionSearchDTO.getCustomerId() != null) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), vehicleTransmissionSearchDTO.getCustomerId()));
            }
            */

            /*
            if (vehicleTransmissionSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), vehicleTransmissionSearchDTO.getPaymentMethodId()));
            }

            if (vehicleTransmissionSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), vehicleTransmissionSearchDTO.getPaymentStatusId()));
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

        TypedQuery<VehicleTransmission> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<VehicleTransmission> resultList = query.getResultList();

        List<VehicleTransmissionDTO> dtoList = resultList.stream()
                .map(VehicleTransmissionMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(vehicleTransmissionSearchDTO));
    }

    public Long countSearchPage(VehicleTransmissionSearchDTO vehicleTransmissionSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VehicleTransmission> root = cq.from(VehicleTransmission.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (vehicleTransmissionSearchDTO != null) {
            /*
            if (vehicleTransmissionSearchDTO.getCustomerId() != null) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("customerId"), vehicleTransmissionSearchDTO.getCustomerId()));
            }
            */

            /*
            if (vehicleTransmissionSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), vehicleTransmissionSearchDTO.getPaymentMethodId()));
            }

            if (vehicleTransmissionSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), vehicleTransmissionSearchDTO.getPaymentStatusId()));
            }
            */
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<VehicleTransmission> root, Sort.Order order) {
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