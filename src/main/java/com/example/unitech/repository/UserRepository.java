package com.example.unitech.repository;


import com.example.unitech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    User findByUsername(String username);
   Optional<User> findByEmailIgnoreCase(String email);


    User findByPin(String pin);


}
