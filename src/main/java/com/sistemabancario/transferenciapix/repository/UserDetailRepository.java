package com.sistemabancario.transferenciapix.repository;

import com.sistemabancario.transferenciapix.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail , String> {
    Optional<UserDetail> findByUser_id(String userId);

    @Query("""
            SELECT ud
            FROM UserDetail ud
            JOIN FETCH ud.user u
            WHERE ud.cidade = :cidade
            """)
    List<UserDetail> findByCidade(@Param("cidade") String cidade);
}
