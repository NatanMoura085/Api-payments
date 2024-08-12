package com.api_payments.domain.repository;

import com.api_payments.domain.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {
    Optional<UserDetails> findBylogin(String login);
    @Query("SELECT l FROM Login l WHERE l.login = :login")
   Login findByLogin2(@Param("login") String login);

}
