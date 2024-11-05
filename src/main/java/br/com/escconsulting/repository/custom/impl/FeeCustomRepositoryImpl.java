package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.fee.FeeDTO;
import br.com.escconsulting.dto.fee.FeeSearchDTO;
import br.com.escconsulting.entity.Fee;
import br.com.escconsulting.mapper.FeeMapper;
import br.com.escconsulting.repository.FeeRepository;
import br.com.escconsulting.repository.custom.FeeCustomRepository;
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
public class FeeCustomRepositoryImpl extends SimpleJpaRepository<Fee, UUID> implements FeeCustomRepository {

    private final EntityManager entityManager;

    private final FeeRepository feeRepository;

    public FeeCustomRepositoryImpl(EntityManager entityManager, FeeRepository feeRepository) {
        super(Fee.class, entityManager);
        this.entityManager = entityManager;
        this.feeRepository = feeRepository;
    }

    public Page<FeeDTO> searchPage(FeeSearchDTO feeSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Fee> cq = cb.createQuery(Fee.class);
        Root<Fee> root = cq.from(Fee.class);

        Predicate spec = cb.conjunction();

        if (feeSearchDTO != null) {

            // Adiciona condição de filtro para o campo searchValue em todos os campos desejados
            if (feeSearchDTO.getGlobalFilter() != null && !feeSearchDTO.getGlobalFilter().isEmpty()) {
                String likePattern = "%" + feeSearchDTO.getGlobalFilter().toUpperCase() + "%";

                Predicate namePredicate = cb.like(cb.upper(root.get("feeType")), likePattern);
                // Adicione outros campos aqui da mesma forma
                Predicate enabledPredicate = cb.like(cb.upper(root.get("enabled").as(String.class)), likePattern);

                // Combine todos os predicados em uma disjunção (OR)
                spec = cb.and(spec, cb.or(namePredicate, enabledPredicate));
            }

            // Verifica se o campo 'feeType' está presente e adiciona a condição com like '%...%'
            if (feeSearchDTO.getFeeType() != null && !feeSearchDTO.getFeeType().isEmpty()) {
                String likePattern = "%" + feeSearchDTO.getFeeType() + "%";
                spec = cb.and(spec, cb.like(cb.upper(root.get("feeType")), likePattern.toUpperCase()));
            }

            // Verifica se o campo 'enabled' não é nulo e adiciona a condição
            if (feeSearchDTO.getEnabled() != null) {
                spec = cb.and(spec, cb.equal(root.get("enabled"), feeSearchDTO.getEnabled()));
            }
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

        TypedQuery<Fee> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Fee> resultList = query.getResultList();

        List<FeeDTO> dtoList = resultList.stream()
                .map(FeeMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(feeSearchDTO));
    }

    public Long countSearchPage(FeeSearchDTO feeSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Fee> root = cq.from(Fee.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (feeSearchDTO != null) {

            // Adiciona condição de filtro para o campo searchValue em todos os campos desejados
            if (feeSearchDTO.getGlobalFilter() != null && !feeSearchDTO.getGlobalFilter().isEmpty()) {
                String likePattern = "%" + feeSearchDTO.getGlobalFilter().toUpperCase() + "%";

                Predicate namePredicate = cb.like(cb.upper(root.get("feeType")), likePattern);
                // Adicione outros campos aqui da mesma forma
                Predicate enabledPredicate = cb.like(cb.upper(root.get("enabled").as(String.class)), likePattern);

                // Combine todos os predicados em uma disjunção (OR)
                spec = cb.and(spec, cb.or(namePredicate, enabledPredicate));
            }

            // Verifica se o campo 'paymentStatusName' está presente e adiciona a condição com like '%...%'
            if (feeSearchDTO.getFeeType() != null && !feeSearchDTO.getFeeType().isEmpty()) {
                String likePattern = "%" + feeSearchDTO.getFeeType() + "%";
                spec = cb.and(spec, cb.like(cb.upper(root.get("feeType")), likePattern.toUpperCase()));
            }

            // Verifica se o campo 'enabled' não é nulo e adiciona a condição
            if (feeSearchDTO.getEnabled() != null) {
                spec = cb.and(spec, cb.equal(root.get("enabled"), feeSearchDTO.getEnabled()));
            }
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}