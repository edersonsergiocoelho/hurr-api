package br.com.escconsulting.repository.impl;

import br.com.escconsulting.dto.file.approved.FileApprovedSearchDTO;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.repository.FileApprovedCustomRepository;
import br.com.escconsulting.repository.FileApprovedRepository;
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
public class FileApprovedCustomRepositoryImpl extends SimpleJpaRepository<FileApproved, UUID> implements FileApprovedCustomRepository {

    private final EntityManager entityManager;

    private final FileApprovedRepository fileApprovedRepository;

    public FileApprovedCustomRepositoryImpl(EntityManager entityManager, FileApprovedRepository fileApprovedRepository) {
        super(FileApproved.class, entityManager);
        this.entityManager = entityManager;
        this.fileApprovedRepository = fileApprovedRepository;
    }

    public Page<FileApproved> searchPage(FileApprovedSearchDTO fileApprovedSearchDTO, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<FileApproved> cq = cb.createQuery(FileApproved.class);
        Root<FileApproved> root = cq.from(FileApproved.class);

        Predicate spec = cb.conjunction();

        if (fileApprovedSearchDTO != null) {

            if (fileApprovedSearchDTO.getFilter() != null && ! fileApprovedSearchDTO.getFilter().isEmpty()) {

                if (fileApprovedSearchDTO.getFilter().equalsIgnoreCase("AGUARDANDO_APROVACAO")) {
                    spec = cb.and(
                            cb.isNull(root.get("approvedBy")),
                            cb.isNull(root.get("reprovedBy"))
                    );
                }
            }

            if (fileApprovedSearchDTO.getApprovedBy() != null) {
                spec = cb.and(spec, cb.equal(root.get("approvedBy"), fileApprovedSearchDTO.getApprovedBy()));
            }

            if (fileApprovedSearchDTO.getReprovedBy() != null) {
                spec = cb.and(spec, cb.equal(root.get("reprovedBy"), fileApprovedSearchDTO.getReprovedBy()));
            }

            if (fileApprovedSearchDTO.getFileTable() != null) {
                spec = cb.and(spec, cb.equal(root.get("fileTable"), fileApprovedSearchDTO.getFileTable()));
            }

            if (fileApprovedSearchDTO.getFileType() != null) {
                spec = cb.and(spec, cb.equal(root.get("fileType"), fileApprovedSearchDTO.getFileType()));
            }

            if (fileApprovedSearchDTO.getEnabled() != null) {
                spec = cb.and(spec, cb.equal(root.get("enabled"), fileApprovedSearchDTO.getEnabled()));
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

        TypedQuery<FileApproved> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<FileApproved> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, this.countSearchPage(fileApprovedSearchDTO));
    }

    public Long countSearchPage(FileApprovedSearchDTO fileApprovedSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FileApproved> root = cq.from(FileApproved.class);
        cq.select(cb.count(root));

        Predicate spec = cb.conjunction();

        if (fileApprovedSearchDTO != null) {
            if (fileApprovedSearchDTO.getApprovedBy() != null) {
                spec = cb.and(spec, cb.equal(root.get("approvedBy"), fileApprovedSearchDTO.getApprovedBy()));
            }

            if (fileApprovedSearchDTO.getReprovedBy() != null) {
                spec = cb.and(spec, cb.equal(root.get("reprovedBy"), fileApprovedSearchDTO.getReprovedBy()));
            }

            if (fileApprovedSearchDTO.getFileTable() != null) {
                spec = cb.and(spec, cb.equal(root.get("fileTable"), fileApprovedSearchDTO.getFileTable()));
            }

            if (fileApprovedSearchDTO.getFileType() != null) {
                spec = cb.and(spec, cb.equal(root.get("fileType"), fileApprovedSearchDTO.getFileType()));
            }

            if (fileApprovedSearchDTO.getEnabled() != null) {
                spec = cb.and(spec, cb.equal(root.get("enabled"), fileApprovedSearchDTO.getEnabled()));
            }
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getSingleResult();
    }
}