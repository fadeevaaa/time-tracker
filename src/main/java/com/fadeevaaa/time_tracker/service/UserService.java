package com.fadeevaaa.time_tracker.service;

import com.fadeevaaa.time_tracker.models.User;
import com.fadeevaaa.time_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    public User currentUser;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    public String saveUser(User user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            return "Пользователь с таким логином уже существует";
        }
        else {
            userRepository.save(user);
            return "Пользователь успешно зарегистрирован";
        }
    }

    public String login(String login, String password) {
        Optional<User> optionalUser = userRepository.findByLogin(login);
        try {
            currentUser = optionalUser.get().getPassword().equals(password) ? optionalUser.get() : null;
            if (currentUser == null) {
                throw new NoSuchElementException();
            }
            return "";
        } catch (NoSuchElementException e) {
            return "Неверное имя пользователя или пароль";
        }
    }

    public void editUser(User updatedUser) {
        if (!updatedUser.getName().isEmpty()) currentUser.setName(updatedUser.getName());
        if (!updatedUser.getSurname().isEmpty()) currentUser.setSurname(updatedUser.getSurname());
        if (!updatedUser.getPassword().isEmpty()) currentUser.setPassword(updatedUser.getPassword());
        userRepository.save(currentUser);
    }

    public void deleteUser() {
        userRepository.delete(currentUser);
        currentUser = null;
    }
}
