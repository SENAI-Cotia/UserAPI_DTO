package com.user_api.service;

import com.user_api.dto.UserRequestDTO;
import com.user_api.dto.UserResponseDTO;
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

    public UserResponseDTO create(UserRequestDTO user) {
        if (userRepository.existsByEmail(user.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ja cadastrado");
        }

        User newUser = UserMapper.toRequestDTO(user);
        User user1 = userRepository.save(newUser);

        return UserMapper.toResponseDTO(user1);
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(user -> UserMapper.toResponseDTO(user)).toList();
    }

    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        return UserMapper.toResponseDTO(user);
    }

}
