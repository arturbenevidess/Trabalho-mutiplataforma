package com.app.user_service.modules.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import com.app.user_service.modules.dtos.AuthUserRequestDTO;
import com.app.user_service.modules.dtos.AuthUserResponseDTO;
import com.app.user_service.modules.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class AuthUserService {

  @Value("${security.token.secret.user}")
  private String secretKey;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthUserResponseDTO execute(AuthUserRequestDTO authUserRequestDTO)
          throws AuthenticationException {
    var user = this.userRepository.findByEmail(authUserRequestDTO.email())
            .orElseThrow(() -> {
              throw new UsernameNotFoundException("Nome de usuário ou senha incorretos");
            });

    var passwordMatches = this.passwordEncoder
            .matches(authUserRequestDTO.password(), user.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
    var token = JWT.create()
            .withIssuer("java")
            .withSubject(user.getId().toString())
            .withClaim("roles", Arrays.asList("USER"))
            .withExpiresAt(expiresIn)
            .sign(algorithm);

    var authCandidateResponse = AuthUserResponseDTO.builder()
            .access_token(token)
            .expires_in(expiresIn.toEpochMilli())
            .build();

    return authCandidateResponse;
  }
}
