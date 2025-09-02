package br.futurodev.jmt.m2s11.configs;

import br.futurodev.jmt.m2s11.entidades.UsuarioEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtConfig {

    // Chave precisa ser Muito forte
    private final String SECRET_KEY = "cyJrrNONxDsznXjrbVVMBSHcWyxmevmxApehtKzXn18z09WkF78zlUVUcdwdN2kn7OhorakhCmZaFAHV";

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return parseClaims(token)
                .getBody()
                .getSubject();
    }

    public Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token);
    }

    public String generateToken(UsuarioEntity usuario) {
        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // -> 10 horas
                .claim("id", usuario.getId())
                .claim("nome", usuario.getNome())
                .claim("email", usuario.getEmail())
                .claim("perfil", usuario.getPerfil())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Gera a chave para assinar o token
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
