package com.aastha.insurance.service;

import com.aastha.insurance.dao.UserRepository;
import com.aastha.insurance.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(int id) {
        Optional<User> result= userRepository.findById(id);
        User user = null;
        if(result.isPresent()){
            user=result.get();
        }
        else {
            throw new RuntimeException("User with ID-"+id+" not found");
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
