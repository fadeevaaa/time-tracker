package com.fadeevaaa.time_tracker.user_module.services;

import com.fadeevaaa.time_tracker.user_module.models.entities.User;
import com.fadeevaaa.time_tracker.user_module.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    User user = new User("Ivan", "Ivanov", "vanya", "123");

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void saveUserFailedTest() {
        String login = "vanya";
        when(userRepository.existsByLogin(login)).thenReturn(true);

        String expected = "Пользователь с таким логином уже существует";

        assertEquals(expected, userService.saveUser(user));
    }

    @Test
    void saveUserSuccessfulTest() {
        String login = "vanya";
        when(userRepository.existsByLogin(login)).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        String expected = "Пользователь успешно зарегистрирован";

        assertEquals(expected, userService.saveUser(user));
    }

    @Test
    void loginFailedTest() {
        String login = "vanya";

        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));

        assertNull(userService.login("vanya", "12"));
        assertNull(userService.login("manya", "123"));
        assertNull(userService.login(null, null));
    }

    @Test
    void loginSuccessfulTest() {
        String login = "vanya";

        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));

        assertEquals(user, userService.login(login, "123"));
    }

    @Test
    void editUserPasswordTest() {
        User currentUser = user;
        userService.setCurrentUser(currentUser);
        User updatedUser = new User("", "", "vanya", "1234");

        userService.editUser(updatedUser);
        assertEquals(updatedUser.getPassword(), currentUser.getPassword());
        verify(userRepository).save(currentUser);
    }

    @Test
    void editUserNameTest() {
        User currentUser = user;
        userService.setCurrentUser(currentUser);
        User updatedUser1 = new User("Vanya", "", "vanya", "");

        userService.editUser(updatedUser1);
        assertEquals(updatedUser1.getName(), currentUser.getName());
        verify(userRepository).save(currentUser);
    }

    @Test
    void editUserSurnameTest() {
        User currentUser = user;
        userService.setCurrentUser(currentUser);
        User updatedUser2 = new User("", "Ivanchenko", "vanya", "");

        userService.editUser(updatedUser2);
        assertEquals(updatedUser2.getSurname(), currentUser.getSurname());
        verify(userRepository).save(currentUser);
    }

    @Test
    void editUserWithoutChangesTest() {
        User currentUser = user;
        userService.setCurrentUser(currentUser);
        User updatedUser3 = new User("", "", "vanya", "");

        userService.editUser(updatedUser3);
        assertEquals(user.toString(), currentUser.toString());
        verify(userRepository).save(currentUser);
    }

    @Test
    void deleteUserTest() {
        User currentUser = user;
        userService.setCurrentUser(currentUser);
        userService.deleteUser();
        assertNull(userService.getCurrentUser());
    }
}