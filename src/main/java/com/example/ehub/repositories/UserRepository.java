package com.example.ehub.repositories;

import com.example.ehub.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select u from UserEntity u where u.status='ACTIVE' " +
            "and (lower(u.firstName) like :keyword " +
            "or lower(u.lastName) like :keyword " +
            "or lower(u.username) like :keyword " +
            "or lower(u.phone) like :keyword " +
            "or lower(u.email) like :keyword)")
    Page<UserEntity> searchByKeyword(String keyword, Pageable pageable);

    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
