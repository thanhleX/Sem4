package test.project4v2.JWT;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import test.project4v2.exception.CustomException;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class JWTUtility {
    @Value("${jwt.secret}")
    private String secretKey;

    public CompletableFuture<String> generateToken(String username) {

    return createToken(Map.of(), username);
    }

    private CompletableFuture<String> createToken(Map<String, Object> claims, String subject) {
        byte[] keyBytes = Base64.getDecoder().decode(this.secretKey);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return CompletableFuture.supplyAsync(() -> Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(key)
                .compact());
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        try {
            return extractAllClaims(token).getSubject();
        }catch (Exception e) {
            System.out.println("HUUU: " + e.getMessage());
           return null;
        }
    }

    private Claims extractAllClaims(String token) {
        System.out.println("11111111111");
        try {
//            javax.xml.bind.DatatypeConverter
            byte[] keyBytes = Base64.getDecoder().decode(this.secretKey);
            SecretKey key = Keys.hmacShaKeyFor(keyBytes);
            Jwt<?, ?> parse = Jwts.parser().verifyWith(key)
                    .build()
                    .parse(token);
            return (Claims) parse.getPayload();
        }catch (Exception e) {
            System.out.println("33333333333333333333333333: " + e.getMessage());
            return null;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}


