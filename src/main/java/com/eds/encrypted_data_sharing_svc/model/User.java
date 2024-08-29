package com.eds.encrypted_data_sharing_svc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String publicKey; // Base64 encoded
    @Column(nullable = false, columnDefinition = "TEXT")
    private String privateKey; // Base64 encoded
}