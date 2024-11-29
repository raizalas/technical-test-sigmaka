package com.sigmaka.caseestudy.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

  private final Logger log = LoggerFactory.getLogger(AuthService.class);

  private final JwtEncoder jwtEncoder;

  public AuthService(JwtEncoder jwtEncoder) {
    this.jwtEncoder = jwtEncoder;
  }

  public String generateToken(Authentication auth) {
    Instant now = Instant.now();

    String scope = auth.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("sigmaka")
        .issuedAt(now)
        .expiresAt(now.plus(12, ChronoUnit.HOURS))
        .subject(auth.getName())
        .claim("scope", scope)
        .build();

    log.info(claims.toString());
    log.info("Scope:{}", scope);

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }
}
