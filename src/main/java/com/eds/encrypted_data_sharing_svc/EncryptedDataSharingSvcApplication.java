package com.eds.encrypted_data_sharing_svc;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eds.encrypted_data_sharing_svc.util.EncryptionUtil;

@SpringBootApplication
public class EncryptedDataSharingSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptedDataSharingSvcApplication.class, args);
		try {
			KeyPair keyPair = EncryptionUtil.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			// Encode keys to Base64 for storage
			String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
			String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

			System.out.println("Public Key: " + publicKeyBase64);
			System.out.println("Private Key: " + privateKeyBase64);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}