package com.aastha.insurance.service;

import com.aastha.insurance.entity.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public List<User> findAll();
    public User findUserById(int id);
    public void updateUser(User user);
    public void deleteUser(int id);
}
