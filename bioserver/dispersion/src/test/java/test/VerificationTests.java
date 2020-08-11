package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dispersion.invoker.ApplicationRunner;
import com.dispersion.service.VerifyingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public class VerificationTests {
	
	@Autowired
	private DataSource dataSource;
	
	@MockBean
    private VerifyingService verifyingService;

	private static final Logger log = LoggerFactory.getLogger(VerificationTests.class);
	
	private static Properties properties = new Properties();
	
	private static InputStream propertiesInputStream;
	
	public String getPublicKey(String institution) {
		
		try {
			String key = null;
    		String sql = "SELECT BIOBANK_NAME, \"KEY\" FROM PUBLIC.BIOBANK_KEYS WHERE BIOBANK_NAME = ?";
			Connection connection = dataSource.getConnection();
			
			PreparedStatement getInstitutionPublicKeyStatement = connection.prepareStatement(sql);
			getInstitutionPublicKeyStatement.setString(1, institution);

			ResultSet rs = getInstitutionPublicKeyStatement.executeQuery();
			
			while (rs.next()) {
				key = rs.getString("KEY");
            	System.out.printf("BIOBANK_NAME: %s%n  \"KEY\": %s%n", rs.getString("BIOBANK_NAME"), rs.getString("KEY"));
            }
			
			return key;
			
    	} catch (Exception e) {
			log.error("Error occured while getting public key", e);
			throw new RuntimeException("Error occured while getting public key");
		}
	}
	
	public static PublicKey convertPublicKey(String key) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, URISyntaxException {
		try {
			key.replaceAll("\n", "");
			System.out.println(key);
			
			byte[] decoded = Base64.decodeBase64(key);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFactory.generatePublic(keySpec);
			
			return pubKey;
		} catch (Exception e) {
			log.error("Error occured while converting public key", e);
			throw new RuntimeException("Error occured while converting public key");
		}
    }
	
	public static PrivateKey convertPrivateKey() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, URISyntaxException {
		try {
			propertiesInputStream = VerificationTests.class.getClassLoader().getResourceAsStream("application.properties");
			properties.load(propertiesInputStream);
			
			String privateKeyB64 = new String(Files.readAllBytes(Paths.get(properties.getProperty("private.key.path"))));
			privateKeyB64 = privateKeyB64.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replaceAll("\n", "");
			
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
	
	public String creatingTokenTest() throws IllegalArgumentException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, URISyntaxException {
		try {
			RSAPrivateKey privateKey = (RSAPrivateKey) convertPrivateKey();
			
			Algorithm algorithmRS = Algorithm.RSA256(null, privateKey);
			
			propertiesInputStream = VerificationTests.class.getClassLoader().getResourceAsStream("application.properties");
			properties.load(propertiesInputStream);
			
			String institution = new String(properties.getProperty("institution.name"));
			
			String token = JWT.create()
			        .withIssuer("auth0")
			        .withClaim("institution", institution)
			        .sign(algorithmRS);
					
			assertThat(token, is(notNullValue()));
			
			return token;
		} catch (Exception e) {
			log.error("Error occured while creating token", e);
			throw new RuntimeException("Error occured while creating token");
		}
		
	}
	
	public void verifyTokenTest(String token) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, URISyntaxException {
		try {
			
			DecodedJWT decodedToken = JWT.decode(token);
			
			Claim claim = decodedToken.getClaim("institution");
			
			String key = getPublicKey(claim.asString());
			
			RSAPublicKey publicKey = (RSAPublicKey) convertPublicKey(key);
			
			Algorithm algorithmRS = Algorithm.RSA256(publicKey, null);
			
			algorithmRS.verify(decodedToken);
			
			assertEquals("B1", claim.asString());
		} catch (Exception e) {
			log.error("Error occured while verifying token", e);
			throw new RuntimeException("Error occured while verifying token");
		}
		
	}
	
	@Test
	public void test() throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalArgumentException, IOException, URISyntaxException {
		String token = creatingTokenTest();
		verifyTokenTest(token);
	}
	
}

