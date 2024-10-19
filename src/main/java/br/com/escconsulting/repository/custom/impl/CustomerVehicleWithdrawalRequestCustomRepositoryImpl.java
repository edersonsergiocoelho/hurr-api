package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleWithdrawalRequest;
import br.com.escconsulting.mapper.CustomerVehicleWithdrawalRequestMapper;
import br.com.escconsulting.repository.CustomerVehicleWithdrawalRequestRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleWithdrawalRequestCustomRepository;
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
public class CustomerVehicleWithdrawalRequestCustomRepositoryImpl extends SimpleJpaRepository<CustomerVehicleWithdrawalRequest, UUID> implements CustomerVehicleWithdrawalRequestCustomRepository {

    private final EntityManager entityManager;

    private final CustomerVehicleWithdrawalRequestRepository customerVehicleWithdrawalRequestRepository;

    public CustomerVehicleWithdrawalRequestCustomRepositoryImpl(EntityManager entityManager, CustomerVehicleWithdrawalRequestRepository customerVehicleWithdrawalRequestRepository) {
        super(CustomerVehicleWithdrawalRequest.class, entityManager);
        this.entityManager = entityManager;
        this.customerVehicleWithdrawalRequestRepository = customerVehicleWithdrawalRequestRepository;
    }

    public Page<CustomerVehicleWithdrawalRequestDTO> searchPage(CustomerVehicleWithdrawalRequestSearchDTO customerVehicleWithdrawalRequestSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleWithdrawalRequest> cq = cb.createQuery(CustomerVehicleWithdrawalRequest.class);
        Root<CustomerVehicleWithdrawalRequest> root = cq.from(CustomerVehicleWithdrawalRequest.class);

        root.fetch("customerVehicleBooking", JoinType.LEFT);
        root.fetch("customer", JoinType.LEFT);
        root.fetch("customerVehicleBankAccount", JoinType.LEFT);
        root.fetch("paymentMethod", JoinType.LEFT);
        root.fetch("paymentStatus", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        if (customerVehicleWithdrawalRequestSearchDTO != null) {
            if (customerVehicleWithdrawalRequestSearchDTO.getCpf() != null && ! customerVehicleWithdrawalRequestSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), customerVehicleWithdrawalRequestSearchDTO.getCpf()));
            }

            if (customerVehicleWithdrawalRequestSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerVehicleWithdrawalRequestSearchDTO.getPaymentMethodId()));
            }

            if (customerVehicleWithdrawalRequestSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerVehicleWithdrawalRequestSearchDTO.getPaymentStatusId()));
            }
        }

        cq.where(spec);

        if (pageable.getSort().isSorted()) {
            List<Order> orders = pageable.getSort().stream()
                    .map(order -> buildOrder(cb, root, order))
                    .collect(Collectors.toList());
            cq.orderBy(orders);
        }

        TypedQuery<CustomerVehicleWithdrawalRequest> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerVehicleWithdrawalRequest> resultList = query.getResultList();

        List<CustomerVehicleWithdrawalRequestDTO> dtoList = resultList.stream()
                .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerVehicleWithdrawalRequestSearchDTO));
    }

    public Long countSearchPage(CustomerVehicleWithdrawalRequestSearchDTO customerVehicleWithdrawalRequestSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerVehicleWithdrawalRequest> root = cq.from(CustomerVehicleWithdrawalRequest.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerVehicleWithdrawalRequestSearchDTO != null) {
            if (customerVehicleWithdrawalRequestSearchDTO.getCpf() != null && ! customerVehicleWithdrawalRequestSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), customerVehicleWithdrawalRequestSearchDTO.getCpf()));
            }

            if (customerVehicleWithdrawalRequestSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerVehicleWithdrawalRequestSearchDTO.getPaymentMethodId()));
            }

            if (customerVehicleWithdrawalRequestSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerVehicleWithdrawalRequestSearchDTO.getPaymentStatusId()));
            }
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<CustomerVehicleWithdrawalRequest> root, Sort.Order order) {
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