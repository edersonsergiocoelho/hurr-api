package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoDTO;
import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleFilePhoto;
import br.com.escconsulting.mapper.CustomerVehicleFilePhotoMapper;
import br.com.escconsulting.repository.CustomerVehicleFilePhotoRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleFilePhotoCustomRepository;
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
public class CustomerVehicleFilePhotoCustomRepositoryImpl extends SimpleJpaRepository<CustomerVehicleFilePhoto, UUID> implements CustomerVehicleFilePhotoCustomRepository {

    private final EntityManager entityManager;

    private final CustomerVehicleFilePhotoRepository customerVehicleFilePhotoRepository;

    public CustomerVehicleFilePhotoCustomRepositoryImpl(EntityManager entityManager, CustomerVehicleFilePhotoRepository customerVehicleFilePhotoRepository) {
        super(CustomerVehicleFilePhoto.class, entityManager);
        this.entityManager = entityManager;
        this.customerVehicleFilePhotoRepository = customerVehicleFilePhotoRepository;
    }

    public Page<CustomerVehicleFilePhotoDTO> searchPage(CustomerVehicleFilePhotoSearchDTO customerVehicleFilePhotoSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicleFilePhoto> cq = cb.createQuery(CustomerVehicleFilePhoto.class);
        Root<CustomerVehicleFilePhoto> root = cq.from(CustomerVehicleFilePhoto.class);

        root.fetch("customerVehicleBooking", JoinType.LEFT);
        root.fetch("customer", JoinType.LEFT);
        root.fetch("customerBankAccount", JoinType.LEFT);
        root.fetch("paymentMethod", JoinType.LEFT);
        root.fetch("paymentStatus", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        if (customerVehicleFilePhotoSearchDTO != null) {
            /*
            if (customerVehicleFilePhotoSearchDTO.getCpf() != null && ! customerVehicleFilePhotoSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), customerVehicleFilePhotoSearchDTO.getCpf()));
            }

            if (customerVehicleFilePhotoSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerVehicleFilePhotoSearchDTO.getPaymentMethodId()));
            }

            if (customerVehicleFilePhotoSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerVehicleFilePhotoSearchDTO.getPaymentStatusId()));
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

        TypedQuery<CustomerVehicleFilePhoto> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerVehicleFilePhoto> resultList = query.getResultList();

        List<CustomerVehicleFilePhotoDTO> dtoList = resultList.stream()
                .map(CustomerVehicleFilePhotoMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(customerVehicleFilePhotoSearchDTO));
    }

    public Long countSearchPage(CustomerVehicleFilePhotoSearchDTO customerVehicleFilePhotoSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerVehicleFilePhoto> root = cq.from(CustomerVehicleFilePhoto.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (customerVehicleFilePhotoSearchDTO != null) {
            /*
            if (customerVehicleFilePhotoSearchDTO.getCpf() != null && ! customerVehicleFilePhotoSearchDTO.getCpf().isEmpty()) {
                spec = cb.and(spec, cb.equal(root.get("customer").get("cpf"), customerVehicleFilePhotoSearchDTO.getCpf()));
            }

            if (customerVehicleFilePhotoSearchDTO.getPaymentMethodId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentMethod").get("paymentMethodId"), customerVehicleFilePhotoSearchDTO.getPaymentMethodId()));
            }

            if (customerVehicleFilePhotoSearchDTO.getPaymentStatusId() != null) {
                spec = cb.and(spec, cb.equal(root.get("paymentStatus").get("paymentStatusId"), customerVehicleFilePhotoSearchDTO.getPaymentStatusId()));
            }
            */
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Order buildOrder(CriteriaBuilder cb, Root<CustomerVehicleFilePhoto> root, Sort.Order order) {
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