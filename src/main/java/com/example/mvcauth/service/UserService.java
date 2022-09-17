package com.example.mvcauth.service;

import com.example.mvcauth.model.User;
import com.example.mvcauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(User user) {

        String[] userId = user.getSub().split("\\|");
        user.setSub(userId[1]);

        try {
            Optional<User> opUser = userRepository.findById(user.getSub());
            if (opUser.isPresent()) {
                return;
            }
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getCause());
        }
    }

}
