package com.example.webshopbackend.service;

import com.example.webshopbackend.dao.RoleRepository;
import com.example.webshopbackend.dao.UserRepository;
import com.example.webshopbackend.domain.Checkout;
import com.example.webshopbackend.domain.Role;
import com.example.webshopbackend.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userServiceImplTest;

    @BeforeEach
    void setup() {
        userServiceImplTest = new UserServiceImpl(
                userRepository,
                roleRepository,
                passwordEncoder
        );
    }

   /* @Test
    void canSaveUser() {
        //given
        User user = new User(
                null,
                "Ákos",
                "Kovács",
                "kakoska",
                "kakoska@gmail.com",
                "Password123!",
                new ArrayList<>(),
                new Checkout()
        );

        //when
        userServiceImplTest.saveUser(user);

        //then
        ArgumentCaptor<String> passwordArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(passwordEncoder).encode(passwordArgumentCaptor.capture());

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        String capturedPassword = passwordArgumentCaptor.getValue();
        assertThat(capturedPassword).isEqualTo("Password123!");

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void canSaveRole() {
        //given
        Role role = new Role(
                null,
                "ROLE_USER"
        );

        //when
        userServiceImplTest.saveRole(role);

        //then
        ArgumentCaptor<Role> roleArgumentCaptor = ArgumentCaptor.forClass(Role.class);
        verify(roleRepository).save(roleArgumentCaptor.capture());

        Role capturedRole = roleArgumentCaptor.getValue();
        assertThat(capturedRole).isEqualTo(role);
    }

    @Test
    void canAddRoleToUser() {
        //given
        User user = new User(
                null,
                "Ákos",
                "Kovács",
                "kakoska",
                "kakoska@gmail.com",
                "Password123!",
                new ArrayList<>(),
                new Checkout()
        );

        Role role = new Role(
                null,
                "ROLE_USER"
        );

        given(userRepository.findUserByUsername(user.getUsername()))
                .willReturn(user);

        //when
        userServiceImplTest.addRoleToUser(user.getUsername(), role.getName());

        //then
        ArgumentCaptor<String> userArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(userRepository).findUserByUsername(userArgumentCaptor.capture());

        ArgumentCaptor<String> roleArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(roleRepository).findRoleByName(roleArgumentCaptor.capture());

        String capturedUsername = userArgumentCaptor.getValue();
        assertThat(capturedUsername).isEqualTo(user.getUsername());

        String capturedRoleName = roleArgumentCaptor.getValue();
        assertThat(capturedRoleName).isEqualTo(role.getName());
    }

    @Test
    void canGetUser() {
        //given
        String username = "nmarcell05";

        //when
        userServiceImplTest.getUser(username);

        //then
        ArgumentCaptor<String> userArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(userRepository).findUserByUsername(userArgumentCaptor.capture());

        String capturedUsername = userArgumentCaptor.getValue();
        assertThat(capturedUsername).isEqualTo("nmarcell05");
    }

    @Test
    void canGetUsers() {
        //when
        userServiceImplTest.getUsers();

        //then
        verify(userRepository).findAll();
    }

    @Test
    void willThrowErrorDuringLoadUserByUsername() {
        //given
        String username = "nmarcell05";

        //when
        //then
        assertThatThrownBy(() -> userServiceImplTest.loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("User is not found in database");

        ArgumentCaptor<String> userArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(userRepository).findUserByUsername(userArgumentCaptor.capture());

        String capturedUsername = userArgumentCaptor.getValue();
        assertThat(capturedUsername).isEqualTo("nmarcell05");
    }

    @Test
    void canLoadUserByUsername() {
        //given
        User user = new User(
                null,
                "Ákos",
                "Kovács",
                "kakoska",
                "kakoska@gmail.com",
                "Password123!",
                new ArrayList<>(),
                new Checkout()
        );

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );

        given(userRepository.findUserByUsername(user.getUsername()))
                .willReturn(user);

        //when
        //then
        UserDetails returnedUserDetails = userServiceImplTest.loadUserByUsername(user.getUsername());

        ArgumentCaptor<String> userArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(userRepository).findUserByUsername(userArgumentCaptor.capture());

        String capturedUsername = userArgumentCaptor.getValue();
        assertThat(capturedUsername).isEqualTo(user.getUsername());

        assertThat(returnedUserDetails).isEqualTo(userDetails);
    }*/
}