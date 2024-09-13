package com.emazon.api_stock.infraestructure.securityconfig.jwtconfiguration;

import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;


@Service
public class JwtService {

    @Value(InfraestructureConstants.JWT_SECRET)
    private String secretKey;

    private Key generateKey(){
        byte[] secretAsBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(secretAsBytes);
    }

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    public String extractRole(String jwt){
        return extractAllClaims(jwt).get(InfraestructureConstants.AUTHORITIES).toString();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();
    }
}
