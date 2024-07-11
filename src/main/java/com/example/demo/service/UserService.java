package com.example.demo.service;

import com.example.demo.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUser();

    UserDTO addUser(UserDTO userDTO);

    UserDTO findIdUser(Long id);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(Long id);
}
