package com.enigma.demospringboot.service;

import com.enigma.demospringboot.model.request.LoginRequest;
import com.enigma.demospringboot.model.request.RegisterRequest;

public interface IAuthService {
    String register(RegisterRequest registerRequest);

//    Ga jadi digunakan karena ada class yang implement interface ini
//    @Query("SELECT u FROM User u WHERE u.auth.email = ?1 and u.auth.password = ?2")
//    String login(String email, String password);

    String login(LoginRequest loginRequest);
}
