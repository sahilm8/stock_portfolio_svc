package com.eds.encrypted_data_sharing_svc.repository;

import com.eds.encrypted_data_sharing_svc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}