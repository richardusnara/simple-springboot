package com.enigma.demospringboot.repository;

import com.enigma.demospringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
