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

    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ja cadastrado");
        }

        User novoUser = UserMapper.toDtoRequest(userRequestDTO);


        User use = userRepository.save(novoUser);


        return UserMapper.toDtoResponse(use);
    }

    public List<UserResponseDTO> listar() {
        return userRepository.findAll().stream().map(user -> UserMapper.toDtoResponse(user)).toList();
    }

    public UserResponseDTO buscarId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User não encontrado!"));

        return UserMapper.toDtoResponse(user);
    }
}
