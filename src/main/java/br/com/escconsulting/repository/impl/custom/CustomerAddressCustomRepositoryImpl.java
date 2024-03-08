package br.com.escconsulting.repository.impl.custom;

import br.com.escconsulting.dto.customer.address.CustomerAddressSearchDTO;
import br.com.escconsulting.entity.CustomerAddress;
import br.com.escconsulting.repository.custom.CustomerAddressCustomRepository;
import br.com.escconsulting.repository.CustomerAddressRepository;
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
public class CustomerAddressCustomRepositoryImpl extends SimpleJpaRepository<CustomerAddress, UUID> implements CustomerAddressCustomRepository {

    private final EntityManager entityManager;

    private final CustomerAddressRepository customerAddressRepository;

    public CustomerAddressCustomRepositoryImpl(EntityManager entityManager, CustomerAddressRepository customerAddressRepository) {
        super(CustomerAddress.class, entityManager);
        this.entityManager = entityManager;
        this.customerAddressRepository = customerAddressRepository;
    }

    public Page<CustomerAddress> searchPage(CustomerAddressSearchDTO fileApprovedSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerAddress> cq = cb.createQuery(CustomerAddress.class);
        Root<CustomerAddress> root = cq.from(CustomerAddress.class);

        Predicate spec = cb.conjunction();

        if (fileApprovedSearchDTO != null) {

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

        TypedQuery<CustomerAddress> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<CustomerAddress> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, this.countSearchPage(fileApprovedSearchDTO));
    }

    public Long countSearchPage(CustomerAddressSearchDTO fileApprovedSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CustomerAddress> root = cq.from(CustomerAddress.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (fileApprovedSearchDTO != null) {
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}