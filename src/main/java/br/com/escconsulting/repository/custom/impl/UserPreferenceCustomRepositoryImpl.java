package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.use.preference.UserPreferenceDTO;
import br.com.escconsulting.dto.use.preference.UserPreferenceSearchDTO;
import br.com.escconsulting.entity.UserPreference;
import br.com.escconsulting.mapper.UserPreferenceMapper;
import br.com.escconsulting.repository.UserPreferenceRepository;
import br.com.escconsulting.repository.custom.UserPreferenceCustomRepository;
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
public class UserPreferenceCustomRepositoryImpl extends SimpleJpaRepository<UserPreference, UUID> implements UserPreferenceCustomRepository {

    private final EntityManager entityManager;

    private final UserPreferenceRepository userPreferenceRepository;

    public UserPreferenceCustomRepositoryImpl(EntityManager entityManager, UserPreferenceRepository userPreferenceRepository) {
        super(UserPreference.class, entityManager);
        this.entityManager = entityManager;
        this.userPreferenceRepository = userPreferenceRepository;
    }

    public Page<UserPreferenceDTO> searchPage(UserPreferenceSearchDTO userPreferenceSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserPreference> cq = cb.createQuery(UserPreference.class);
        Root<UserPreference> root = cq.from(UserPreference.class);

        Predicate spec = cb.conjunction();

        if (userPreferenceSearchDTO != null) {

            /*
            // Adiciona condição de filtro para o campo searchValue em todos os campos desejados
            if (userPreferenceSearchDTO.getGlobalFilter() != null && !userPreferenceSearchDTO.getGlobalFilter().isEmpty()) {
                String likePattern = "%" + userPreferenceSearchDTO.getGlobalFilter().toLowerCase() + "%";

                Predicate namePredicate = cb.like(cb.upper(root.get("userPreferenceName")), likePattern);
                // Adicione outros campos aqui da mesma forma
                Predicate enabledPredicate = cb.like(cb.upper(root.get("enabled").as(String.class)), likePattern);

                // Combine todos os predicados em uma disjunção (OR)
                spec = cb.and(spec, cb.or(namePredicate, enabledPredicate));
            }

            // Verifica se o campo 'userPreferenceName' está presente e adiciona a condição com like '%...%'
            if (userPreferenceSearchDTO.getUserPreferenceName() != null && !userPreferenceSearchDTO.getUserPreferenceName().isEmpty()) {
                String likePattern = "%" + userPreferenceSearchDTO.getUserPreferenceName() + "%";
                spec = cb.and(spec, cb.like(cb.upper(root.get("userPreferenceName")), likePattern.toLowerCase()));
            }

            // Verifica se o campo 'enabled' não é nulo e adiciona a condição
            if (userPreferenceSearchDTO.getEnabled() != null) {
                spec = cb.and(spec, cb.equal(root.get("enabled"), userPreferenceSearchDTO.getEnabled()));
            }
            */
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

        TypedQuery<UserPreference> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<UserPreference> resultList = query.getResultList();

        List<UserPreferenceDTO> dtoList = resultList.stream()
                .map(UserPreferenceMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(userPreferenceSearchDTO));
    }

    public Long countSearchPage(UserPreferenceSearchDTO userPreferenceSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UserPreference> root = cq.from(UserPreference.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (userPreferenceSearchDTO != null) {
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}