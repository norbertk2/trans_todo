package com.trans.todos.api.v1.services;

import com.trans.todos.api.v1.mapper.UserMapper;
import com.trans.todos.api.v1.model.UserDTO;
import com.trans.todos.api.v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> userMapper.userToUserDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userMapper.userToUserDTO(userRepository.save(userMapper.userDTOToUser(userDTO)));

    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.userToUserDTO(userRepository.findById(id).get());
    }

    @Override
    public UserDTO findByName(String login) {
        return userMapper.userToUserDTO(userRepository.findByLogin(login));
    }
}
