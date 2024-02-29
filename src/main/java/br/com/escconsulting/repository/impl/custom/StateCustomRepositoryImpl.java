package br.com.escconsulting.repository.impl.custom;

import br.com.escconsulting.dto.state.StateSearchDTO;
import br.com.escconsulting.entity.State;
import br.com.escconsulting.repository.StateRepository;
import br.com.escconsulting.repository.custom.StateCustomRepository;
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
public class StateCustomRepositoryImpl extends SimpleJpaRepository<State, UUID> implements StateCustomRepository {

    private final EntityManager entityManager;

    private final StateRepository stateRepository;

    public StateCustomRepositoryImpl(EntityManager entityManager, StateRepository stateRepository) {
        super(State.class, entityManager);
        this.entityManager = entityManager;
        this.stateRepository = stateRepository;
    }

    public Page<State> searchPage(StateSearchDTO stateSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<State> cq = cb.createQuery(State.class);
        Root<State> root = cq.from(State.class);

        Predicate spec = cb.conjunction();

        if (stateSearchDTO != null) {

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

        TypedQuery<State> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<State> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, this.countSearchPage(stateSearchDTO));
    }

    public Long countSearchPage(StateSearchDTO stateSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<State> root = cq.from(State.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (stateSearchDTO != null) {
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}