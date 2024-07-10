package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeDTO;
import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeSearchDTO;
import br.com.escconsulting.entity.VehicleFuelType;
import br.com.escconsulting.mapper.VehicleFuelTypeMapper;
import br.com.escconsulting.repository.VehicleFuelTypeRepository;
import br.com.escconsulting.repository.custom.VehicleFuelTypeCustomRepository;
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
public class VehicleFuelTypeCustomRepositoryImpl extends SimpleJpaRepository<VehicleFuelType, UUID> implements VehicleFuelTypeCustomRepository {

    private final EntityManager entityManager;

    private final VehicleFuelTypeRepository vehicleFuelTypeRepository;

    public VehicleFuelTypeCustomRepositoryImpl(EntityManager entityManager, VehicleFuelTypeRepository vehicleFuelTypeRepository) {
        super(VehicleFuelType.class, entityManager);
        this.entityManager = entityManager;
        this.vehicleFuelTypeRepository = vehicleFuelTypeRepository;
    }

    public Page<VehicleFuelTypeDTO> searchPage(VehicleFuelTypeSearchDTO vehicleFuelTypeSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VehicleFuelType> cq = cb.createQuery(VehicleFuelType.class);
        Root<VehicleFuelType> root = cq.from(VehicleFuelType.class);

        root.fetch("customerVehicleBooking", JoinType.LEFT);
        root.fetch("customer", JoinType.LEFT);
        root.fetch("customerBankAccount", JoinType.LEFT);
        root.fetch("paymentMethod", JoinType.LEFT);
        root.fetch("paymentStatus", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        if (vehicleFuelTypeSearchDTO != null) {
            /*
            if (vehicleFuelTypeSearchDTO.getCpf() != null && ! vehicleFuelTypeSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), vehicleFuelTypeSearchDTO.getCpf()));
            }

            if (vehicleFuelTypeSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), vehicleFuelTypeSearchDTO.getPaymentMethodId()));
            }

            if (vehicleFuelTypeSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), vehicleFuelTypeSearchDTO.getPaymentStatusId()));
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

        TypedQuery<VehicleFuelType> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<VehicleFuelType> resultList = query.getResultList();

        List<VehicleFuelTypeDTO> dtoList = resultList.stream()
                .map(VehicleFuelTypeMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(vehicleFuelTypeSearchDTO));
    }

    public Long countSearchPage(VehicleFuelTypeSearchDTO vehicleFuelTypeSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VehicleFuelType> root = cq.from(VehicleFuelType.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (vehicleFuelTypeSearchDTO != null) {
            /*
            if (vehicleFuelTypeSearchDTO.getCpf() != null && ! vehicleFuelTypeSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), vehicleFuelTypeSearchDTO.getCpf()));
            }

            if (vehicleFuelTypeSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), vehicleFuelTypeSearchDTO.getPaymentMethodId()));
            }

            if (vehicleFuelTypeSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), vehicleFuelTypeSearchDTO.getPaymentStatusId()));
            }
            */
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<VehicleFuelType> root, Sort.Order order) {
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