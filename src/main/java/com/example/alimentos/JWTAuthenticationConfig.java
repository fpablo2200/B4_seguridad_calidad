package com.example.alimentos;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.example.alimentos.constante.Constants;

@Configuration
public class JWTAuthenticationConfig {

    public String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities =
            AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");



    Map<String, Object> claims = new HashMap<>();
    claims.put("authorities", grantedAuthorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()));

    String token = Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuer(Constants.ISSUER_INFO)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_TIME))
            .signWith(Constants.getSigningKey(Constants.SUPER_SECRET_KEY), SignatureAlgorithm.HS256)
            .compact();

    return Constants.TOKEN_BEARER_PREFIX + token;
    }

}

