package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.repository.UserRoleRepository;
import br.com.escconsulting.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole findById(UUID id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> saveAll(List<UserRole> userRoles) {
        return userRoleRepository.saveAll(userRoles);
    }

    @Override
    public UserRole update(UUID id, UserRole review) {
        UserRole existingReview = findById(id);
        //existingReview.setReview(review.getReview());
        //existingReview.setRating(review.getRating());

        return userRoleRepository.save(existingReview);
    }

    @Override
    public void delete(UUID id) {
        UserRole review = findById(id);
        userRoleRepository.delete(review);
    }
}