package com.company.usercreation.repository;

import com.company.usercreation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);
    boolean existsByMobile(String mobile);
    Optional<User> findByEmail(String email);
    List<User> findByDeletedFalse();
}