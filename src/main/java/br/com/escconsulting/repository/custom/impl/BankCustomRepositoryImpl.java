package br.com.escconsulting.repository.custom.impl;

import br.com.escconsulting.dto.bank.BankDTO;
import br.com.escconsulting.dto.bank.BankSearchDTO;
import br.com.escconsulting.entity.Bank;
import br.com.escconsulting.mapper.BankMapper;
import br.com.escconsulting.repository.BankRepository;
import br.com.escconsulting.repository.custom.BankCustomRepository;
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
public class BankCustomRepositoryImpl extends SimpleJpaRepository<Bank, UUID> implements BankCustomRepository {

    private final EntityManager entityManager;

    private final BankRepository bankRepository;

    public BankCustomRepositoryImpl(EntityManager entityManager, BankRepository bankRepository) {
        super(Bank.class, entityManager);
        this.entityManager = entityManager;
        this.bankRepository = bankRepository;
    }

    public Page<BankDTO> searchPage(BankSearchDTO bankSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bank> cq = cb.createQuery(Bank.class);
        Root<Bank> root = cq.from(Bank.class);

        // Adiciona fetch para a entidade File
        root.fetch("file", JoinType.LEFT); // Supondo que seja um fetch à esquerda (LEFT JOIN)

        Predicate spec = cb.conjunction();

        if (bankSearchDTO != null) {

            // Adiciona condição de filtro para o campo searchValue em todos os campos desejados
            if (bankSearchDTO.getGlobalFilter() != null && !bankSearchDTO.getGlobalFilter().isEmpty()) {
                String likePattern = "%" + bankSearchDTO.getGlobalFilter().toUpperCase() + "%";

                Predicate namePredicate = cb.like(cb.upper(root.get("bankName")), likePattern);
                // Adicione outros campos aqui da mesma forma
                Predicate enabledPredicate = cb.like(cb.upper(root.get("enabled").as(String.class)), likePattern);

                // Combine todos os predicados em uma disjunção (OR)
                spec = cb.and(spec, cb.or(namePredicate, enabledPredicate));
            }

            // Verifica se o campo 'paymentStatusName' está presente e adiciona a condição com like '%...%'
            if (bankSearchDTO.getBankName() != null && !bankSearchDTO.getBankName().isEmpty()) {
                String likePattern = "%" + bankSearchDTO.getBankName() + "%";
                spec = cb.and(spec, cb.like(cb.upper(root.get("bankName")), likePattern.toUpperCase()));
            }

            // Verifica se o campo 'enabled' não é nulo e adiciona a condição
            if (bankSearchDTO.getEnabled() != null) {
                spec = cb.and(spec, cb.equal(root.get("enabled"), bankSearchDTO.getEnabled()));
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

        TypedQuery<Bank> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Bank> resultList = query.getResultList();

        List<BankDTO> dtoList = resultList.stream()
                .map(BankMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, this.countSearchPage(bankSearchDTO));
    }

    public Long countSearchPage(BankSearchDTO bankSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bank> root = cq.from(Bank.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (bankSearchDTO != null) {

            // Adiciona condição de filtro para o campo searchValue em todos os campos desejados
            if (bankSearchDTO.getGlobalFilter() != null && !bankSearchDTO.getGlobalFilter().isEmpty()) {
                String likePattern = "%" + bankSearchDTO.getGlobalFilter().toUpperCase() + "%";

                Predicate namePredicate = cb.like(cb.upper(root.get("bankName")), likePattern);
                // Adicione outros campos aqui da mesma forma
                Predicate enabledPredicate = cb.like(cb.upper(root.get("enabled").as(String.class)), likePattern);

                // Combine todos os predicados em uma disjunção (OR)
                spec = cb.and(spec, cb.or(namePredicate, enabledPredicate));
            }

            // Verifica se o campo 'paymentStatusName' está presente e adiciona a condição com like '%...%'
            if (bankSearchDTO.getBankName() != null && !bankSearchDTO.getBankName().isEmpty()) {
                String likePattern = "%" + bankSearchDTO.getBankName() + "%";
                spec = cb.and(spec, cb.like(cb.upper(root.get("bankName")), likePattern.toUpperCase()));
            }

            // Verifica se o campo 'enabled' não é nulo e adiciona a condição
            if (bankSearchDTO.getEnabled() != null) {
                spec = cb.and(spec, cb.equal(root.get("enabled"), bankSearchDTO.getEnabled()));
            }
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}