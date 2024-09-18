package com.bankingApp.service;

import com.bankingApp.entity.User;
import com.bankingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(User user){
        return repository.save(user);
    }

    public User getUserById(Long userId){
        return repository.findById(userId).orElse(null );
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User updateUser(User user){
        return repository.save(user);
    }

    public void deleteUser(Long userId){
        repository.deleteById(userId);
    }
}
