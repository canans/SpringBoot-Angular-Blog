package com.example.demo.security;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;

import javax.annotation.PostConstruct;

import javassist.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.security.cert.CertificateException;

// open cmd and write bottoms to determine private key
// keytool -genkey -alias springblog -keyalg RSA -keystore springblog.jks -keysize 2048
// keytool -importkeystore -srckeystore springblog.jks -destkeystore springblog.jks -deststoretype pkcs12
@Service
public class JwtProvider {
    private KeyStore keyStore;

    @PostConstruct
    public void init() throws Exception {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "hcelal".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new NotFoundException("Exception occured while loading keystore");
        }

    }

    public String generateToken(Authentication authentication) throws Exception {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() throws Exception {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "hcelal".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new NotFoundException("Exception occured while retrieving public key from keystore");
        }
    }

    public boolean validateToken(String jwt) throws Exception {
        Jwts.parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublickey() throws Exception {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new NotFoundException("Exception occured while retrieving public key from keystore");
        }
    }

    public String getUsernameFromJWT(String token) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(getPublickey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
