package br.com.escconsulting.repository.impl.custom;

import br.com.escconsulting.dto.address.address.type.AddressAddressTypeSearchDTO;
import br.com.escconsulting.entity.AddressAddressType;
import br.com.escconsulting.repository.AddressAddressTypeRepository;
import br.com.escconsulting.repository.custom.AddressAddressTypeCustomRepository;
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
public class AddressAddressTypeCustomRepositoryImpl extends SimpleJpaRepository<AddressAddressType, UUID> implements AddressAddressTypeCustomRepository {

    private final EntityManager entityManager;

    private final AddressAddressTypeRepository addressAddressAddressTypeRepository;

    public AddressAddressTypeCustomRepositoryImpl(EntityManager entityManager, AddressAddressTypeRepository addressAddressAddressTypeRepository) {
        super(AddressAddressType.class, entityManager);
        this.entityManager = entityManager;
        this.addressAddressAddressTypeRepository = addressAddressAddressTypeRepository;
    }

    public Page<AddressAddressType> searchPage(AddressAddressTypeSearchDTO addressAddressAddressTypeSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AddressAddressType> cq = cb.createQuery(AddressAddressType.class);
        Root<AddressAddressType> root = cq.from(AddressAddressType.class);

        Predicate spec = cb.conjunction();

        if (addressAddressAddressTypeSearchDTO != null) {

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

        TypedQuery<AddressAddressType> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<AddressAddressType> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, this.countSearchPage(addressAddressAddressTypeSearchDTO));
    }

    public Long countSearchPage(AddressAddressTypeSearchDTO addressAddressTypeSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AddressAddressType> root = cq.from(AddressAddressType.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (addressAddressTypeSearchDTO != null) {
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}