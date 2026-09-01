package com.user_api.service;

import com.user_api.dto.UserRequestDTO;
import com.user_api.dto.UserResponseDTO;
import com.user_api.entity.User;
import com.user_api.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
    public class UserService {

private final UserRepository userRepository;

public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
}

public UserResponseDTO create(UserRequestDTO request) {
    if (userRepository.existsByEmail(request.email())) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ja cadastrado");
    }

        User user = toEntity(request);
    user.setCreatedAt(LocalDateTime.now());
    user.setActive(true);

        User saved = userRepository.save(user);
    return toResponseDTO(saved);
}

public List<UserResponseDTO> findAll() {
    return userRepository.findAll().stream()
        .map(this::toResponseDTO)
        .toList();
}

public UserResponseDTO findById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Usuario nao encontrado"));
    return toResponseDTO(user);
}

private User toEntity(UserRequestDTO request) {
    User user = new User();
    user.setName(request.name());
    user.setEmail(request.email());
    user.setPassword(request.password());
    user.setCpf(request.cpf());
    return user;
}

private UserResponseDTO toResponseDTO(User user) {
    return new UserResponseDTO(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getCreatedAt(),
        user.getActive()
        );
}
    }
