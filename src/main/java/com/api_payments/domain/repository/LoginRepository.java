package com.api_payments.domain.repository;

import com.api_payments.domain.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {
    UserDetails findBylogin(String login);
}
