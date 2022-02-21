package com.example.demo.userRepository;

import com.example.demo.usermodel.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Number> {
    Users findUsersByUserId(Long id);
}
