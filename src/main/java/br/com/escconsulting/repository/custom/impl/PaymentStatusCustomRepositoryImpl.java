package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.payment.status.PaymentStatusDTO;
import br.com.escconsulting.dto.payment.status.PaymentStatusSearchDTO;
import br.com.escconsulting.entity.PaymentStatus;
import br.com.escconsulting.mapper.PaymentStatusMapper;
import br.com.escconsulting.repository.PaymentStatusRepository;
import br.com.escconsulting.repository.custom.PaymentStatusCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PaymentStatusCustomRepositoryImpl extends SimpleJpaRepository<PaymentStatus, UUID> implements PaymentStatusCustomRepository {

    private final EntityManager entityManager;

    private final PaymentStatusRepository paymentStatusRepository;

    public PaymentStatusCustomRepositoryImpl(EntityManager entityManager, PaymentStatusRepository paymentStatusRepository) {
        super(PaymentStatus.class, entityManager);
        this.entityManager = entityManager;
        this.paymentStatusRepository = paymentStatusRepository;
    }

    public Page<PaymentStatusDTO> searchPage(PaymentStatusSearchDTO paymentStatusSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentStatus> cq = cb.createQuery(PaymentStatus.class);
        Root<PaymentStatus> root = cq.from(PaymentStatus.class);

        Predicate spec = cb.conjunction();

        if (paymentStatusSearchDTO != null) {
        }

        cq.where(spec);

        if (pageable.getSort().isSorted()) {
            List<Order> orders = pageable.getSort().stream()
                    .map(order -> {
                        String property = order.getProperty();
                        return order.isAscending() ? cb.asc(root.get(property)) : cb.desc(root.get(property));
                    })
                    .collect(Collectors.toList());

            cq.orderBy(orders);
        }

        TypedQuery<PaymentStatus> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<PaymentStatus> resultList = query.getResultList();

        List<PaymentStatusDTO> dtoList = resultList.stream()
                .map(PaymentStatusMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(paymentStatusSearchDTO));
    }

    public Long countSearchPage(PaymentStatusSearchDTO paymentStatusSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PaymentStatus> root = cq.from(PaymentStatus.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (paymentStatusSearchDTO != null) {
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}