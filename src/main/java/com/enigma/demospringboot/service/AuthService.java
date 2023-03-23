package com.enigma.demospringboot.service;

import com.enigma.demospringboot.model.Auth;
import com.enigma.demospringboot.model.User;
import com.enigma.demospringboot.model.request.LoginRequest;
import com.enigma.demospringboot.model.request.RegisterRequest;
import com.enigma.demospringboot.repository.IAuthRepository;
import com.enigma.demospringboot.repository.IUserRepository;
import com.enigma.demospringboot.util.validation.JwtUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService{
    private IAuthRepository authRepository;
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public AuthService(IAuthRepository authRepository, IUserService userService) {
        this.authRepository = authRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public String register(RegisterRequest registerRequest) {
        try {
            Auth auth = modelMapper.map(registerRequest, Auth.class);
            Auth authResult = authRepository.save(auth);

            User user = modelMapper.map(registerRequest, User.class);
            user.setAuth(authResult);
            userService.create(user);

            String token = jwtUtil.generateToken(user.getAuth().getEmail());
            return token;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    @Override
    public String login(LoginRequest loginRequest) {
        try {
            Optional<Auth> auth = authRepository.findById(loginRequest.getEmail());
            if (auth.isEmpty()) throw new RuntimeException("User is not found");
            if (!auth.get().getPassword().equals(loginRequest.getPassword())) {
                throw new RuntimeException("Password is not match");
            }

            String token = jwtUtil.generateToken(loginRequest.getEmail());
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
