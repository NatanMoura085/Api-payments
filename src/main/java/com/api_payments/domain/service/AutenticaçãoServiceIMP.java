package com.api_payments.domain.service;

import com.api_payments.api.dto.AuthDTO;
import com.api_payments.domain.exceptionhandler.UsuarioException;
import com.api_payments.domain.model.Login;
import com.api_payments.domain.repository.LoginRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AutenticaçãoServiceIMP implements AutenticaçaoService {
    private static final Logger logger = LoggerFactory.getLogger(AutenticaçãoServiceIMP.class);

    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        logger.error(login, loginRepository.findBylogin(login));
        return loginRepository.findBylogin(login).orElseThrow(() -> new UsuarioException("Nao encontrou natan o login"));
    }

    @Override
    public String obterToken(AuthDTO authDTO) {
        Login login = loginRepository.findByLogin2(authDTO.getLogin());
        return geraTokenJWT(login);
    }

    public String geraTokenJWT(Login login) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("minha-senha");

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(login.getLogin())
                    .withExpiresAt(geraDataDoToken())
                    .sign(algorithm);


        } catch (JWTCreationException e) {
            throw new RuntimeException("erro no token no auth" + e.getMessage());
        }

    }

    private Instant geraDataDoToken() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }
}
