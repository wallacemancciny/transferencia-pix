package com.sistemabancario.transferenciapix.repository;

import com.sistemabancario.transferenciapix.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail , String> {
    Optional<UserDetail> findByUser_id(String userId);
}
