package com.ducterry.base.commons.config.security.jwt;


import com.ducterry.base.commons.config.security.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
    private final String PREFIX = "JwtProvider";

    private String jwtSecret;
    private int jwtExpiration;

    public String generateToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Date date = new Date();

        return Jwts.builder()
                .setSubject(userPrinciple.getUsername())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + jwtExpiration * 10000L))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature -> Message: {} ",e);
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            LOGGER.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
