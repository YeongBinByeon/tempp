package com.yeongbin.stock.service;

import com.yeongbin.stock.entity.User;
import com.yeongbin.stock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User registerUser(User newUser) {
        return userRepository.save(newUser);
    }

    public List<User> getAllUserList(){
        return userRepository.findAll();
    }
}
