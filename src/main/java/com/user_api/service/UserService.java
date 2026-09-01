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

    public UserResponseDTO create(UserRequestDTO userRequestDTO) {

        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email ja cadastrado"
            );
        }

        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setCpf(userRequestDTO.getCpf());

        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);

        User savedUser = userRepository.save(user);

        return convertToResponseDTO(savedUser);
    }

    public List<UserResponseDTO> findAll() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public UserResponseDTO findById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario nao encontrado"
                ));

        return convertToResponseDTO(user);
    }

    private UserResponseDTO convertToResponseDTO(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getActive()
        );
    }
}
