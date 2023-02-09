package com.example.toyproject.domain.users;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUserId(Long userId);
    Optional<Users> findByEmail(String email);
}
