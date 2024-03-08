package br.com.escconsulting.repository.impl.custom;

import br.com.escconsulting.dto.country.CountrySearchDTO;
import br.com.escconsulting.entity.Country;
import br.com.escconsulting.repository.CountryRepository;
import br.com.escconsulting.repository.custom.CountryCustomRepository;
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
public class CountryCustomRepositoryImpl extends SimpleJpaRepository<Country, UUID> implements CountryCustomRepository {

    private final EntityManager entityManager;

    private final CountryRepository countryRepository;

    public CountryCustomRepositoryImpl(EntityManager entityManager, CountryRepository countryRepository) {
        super(Country.class, entityManager);
        this.entityManager = entityManager;
        this.countryRepository = countryRepository;
    }

    public Page<Country> searchPage(CountrySearchDTO countrySearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> root = cq.from(Country.class);

        Predicate spec = cb.conjunction();

        if (countrySearchDTO != null) {

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

        TypedQuery<Country> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Country> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, this.countSearchPage(countrySearchDTO));
    }

    public Long countSearchPage(CountrySearchDTO countrySearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Country> root = cq.from(Country.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (countrySearchDTO != null) {
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}