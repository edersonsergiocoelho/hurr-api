package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.vehicle.color.VehicleColorDTO;
import br.com.escconsulting.dto.vehicle.color.VehicleColorSearchDTO;
import br.com.escconsulting.entity.VehicleColor;
import br.com.escconsulting.mapper.VehicleColorMapper;
import br.com.escconsulting.repository.VehicleColorRepository;
import br.com.escconsulting.repository.custom.VehicleColorCustomRepository;
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
public class VehicleColorCustomRepositoryImpl extends SimpleJpaRepository<VehicleColor, UUID> implements VehicleColorCustomRepository {

    private final EntityManager entityManager;

    private final VehicleColorRepository vehicleColorRepository;

    public VehicleColorCustomRepositoryImpl(EntityManager entityManager, VehicleColorRepository vehicleColorRepository) {
        super(VehicleColor.class, entityManager);
        this.entityManager = entityManager;
        this.vehicleColorRepository = vehicleColorRepository;
    }

    public Page<VehicleColorDTO> searchPage(VehicleColorSearchDTO vehicleColorSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VehicleColor> cq = cb.createQuery(VehicleColor.class);
        Root<VehicleColor> root = cq.from(VehicleColor.class);

        root.fetch("customerVehicleBooking", JoinType.LEFT);
        root.fetch("customer", JoinType.LEFT);
        root.fetch("customerBankAccount", JoinType.LEFT);
        root.fetch("paymentMethod", JoinType.LEFT);
        root.fetch("paymentStatus", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        if (vehicleColorSearchDTO != null) {
            /*
            if (vehicleColorSearchDTO.getCpf() != null && ! vehicleColorSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), vehicleColorSearchDTO.getCpf()));
            }

            if (vehicleColorSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), vehicleColorSearchDTO.getPaymentMethodId()));
            }

            if (vehicleColorSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), vehicleColorSearchDTO.getPaymentStatusId()));
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

        TypedQuery<VehicleColor> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<VehicleColor> resultList = query.getResultList();

        List<VehicleColorDTO> dtoList = resultList.stream()
                .map(VehicleColorMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(vehicleColorSearchDTO));
    }

    public Long countSearchPage(VehicleColorSearchDTO vehicleColorSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VehicleColor> root = cq.from(VehicleColor.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (vehicleColorSearchDTO != null) {
            /*
            if (vehicleColorSearchDTO.getCpf() != null && ! vehicleColorSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), vehicleColorSearchDTO.getCpf()));
            }

            if (vehicleColorSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), vehicleColorSearchDTO.getPaymentMethodId()));
            }

            if (vehicleColorSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), vehicleColorSearchDTO.getPaymentStatusId()));
            }
            */
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<VehicleColor> root, Sort.Order order) {
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