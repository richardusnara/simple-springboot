package com.enigma.demospringboot.repository;

import com.enigma.demospringboot.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthRepository extends JpaRepository<Auth, String> {
}
