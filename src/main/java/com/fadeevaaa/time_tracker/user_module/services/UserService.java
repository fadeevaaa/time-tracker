package com.fadeevaaa.time_tracker.user_module.services;

import com.fadeevaaa.time_tracker.user_module.models.entities.User;
import com.fadeevaaa.time_tracker.task_module.repositories.TaskRepository;
import com.fadeevaaa.time_tracker.user_module.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final TaskRepository taskRepository;
    private User currentUser;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
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

    public User login(String login, String password) {
        Optional<User> optionalUser = userRepository.findByLogin(login);
        try {
            currentUser = optionalUser.get().getPassword().equals(password) ? optionalUser.get() : null;
            if (currentUser == null) {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return currentUser;
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
