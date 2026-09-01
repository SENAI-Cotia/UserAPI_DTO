package com.user_api.service;

import com.user_api.dto.UserRequestDto;
import com.user_api.dto.UserResponseDto;
import com.user_api.entity.User;
import com.user_api.mapper.UserMapper;
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



    public UserResponseDto create(UserRequestDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ja cadastrado");
        }
        User novoUser = new User();

        novoUser.setName(dto.name());
        novoUser.setEmail(dto.email());
        novoUser.setPassword(dto.password());
        novoUser.setCpf(dto.cpf());
        novoUser.setCreatedAt(LocalDateTime.now());
        novoUser.setActive(true);

        User us = userRepository.save(novoUser);

        return UserMapper.toResponseDto(us);
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map((user -> UserMapper.toResponseDto(user)))
                .toList();
    }

    public UserResponseDto findById(Long id) {
        User us = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Usuario nao encontrado"));
        return UserMapper.toResponseDto(us);

    }
}
