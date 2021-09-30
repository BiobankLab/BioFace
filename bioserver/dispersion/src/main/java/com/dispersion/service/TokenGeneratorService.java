package com.dispersion.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

@Service
public class TokenGeneratorService implements ITokenGenerator {

    @Autowired
    private Environment environment;

    @Autowired
    private DataSource dataSource;

    private static final Logger log = LoggerFactory.getLogger(TokenGeneratorService.class);

    @Override
    public String generateToken() {

        try (Connection connection = dataSource.getConnection()) {

            RSAPrivateKey privateKey = (RSAPrivateKey) convertPrivateKey();

            Algorithm algorithmRS = Algorithm.RSA256(null, privateKey);
            String url = environment.getProperty("dispersion.address") + ":" + environment.getProperty("server.port") + "/";

            Date date = new Date();

            String token = JWT.create().withIssuer("auth0").withClaim("url", url).withIssuedAt(date).sign(algorithmRS);

            String sql = "INSERT INTO PUBLIC.TOKENS VALUES (?,0)";

            PreparedStatement addPermissionTokenStatement = connection.prepareStatement(sql);
            addPermissionTokenStatement.setString(1, token);

            addPermissionTokenStatement.executeUpdate();

            log.info("Token was generated");

            return token;
        } catch (Exception e) {
            log.error("Error occured while creating token", e);
            throw new RuntimeException("Error occured while creating token");
        }
    }

    public PrivateKey convertPrivateKey() {
        try {

            String privateKeyB64 = new String(
                    Files.readAllBytes(Paths.get(environment.getProperty("private.key.path"))));
            privateKeyB64 = privateKeyB64.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "").replaceAll("\n", "");

            byte[] decoded = Base64.decodeBase64(privateKeyB64);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = keyFactory.generatePrivate(keySpec);

            return privKey;
        } catch (Exception e) {
            log.error("Error occured while getting private key", e);
            throw new RuntimeException("Error occured while getting private key");
        }
    }

}
