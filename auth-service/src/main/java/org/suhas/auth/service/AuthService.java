package org.suhas.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.suhas.auth.domain.User;
import org.suhas.auth.dto.AuthResponse;
import org.suhas.auth.dto.LoginRequest;
import org.suhas.auth.dto.RegisterRequest;
import org.suhas.auth.repository.UserRespository;
import org.suhas.auth.security.JwtUtil;
import org.suhas.common.dto.UserDTO;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthResponse register(RegisterRequest registerRequest) {

        if (userRespository.existsByEmail(registerRequest.email())) {
            throw new RuntimeException("Email already exists");
        }
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .name(registerRequest.name())
                .role("USER")
                .build();
        user = userRespository.save(user);
        return generateAuthResponse(user);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRespository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return generateAuthResponse(user);
    }

    private AuthResponse generateAuthResponse(User user) {

        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();

        return new AuthResponse(token, userDTO);
    }
}
