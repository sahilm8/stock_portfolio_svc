package com.eds.encrypted_data_sharing_svc.service;

import com.eds.encrypted_data_sharing_svc.model.User;
import com.eds.encrypted_data_sharing_svc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import com.eds.encrypted_data_sharing_svc.util.EncryptionUtil;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        String publicKeyBase64 = null;
        String privateKeyBase64 = null;

        try {
            KeyPair keyPair = EncryptionUtil.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Encode keys to Base64 for storage
            publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            user.setPublicKey(publicKeyBase64);
            user.setPrivateKey(privateKeyBase64);

            log.info("Public Key: " + publicKeyBase64);
            log.info("Private Key: " + privateKeyBase64);

            return userRepository.save(user);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate key pair", e.getCause());
        }
    }
}