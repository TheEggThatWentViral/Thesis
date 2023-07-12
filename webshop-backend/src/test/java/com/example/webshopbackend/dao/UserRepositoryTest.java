package com.example.webshopbackend.dao;

import com.example.webshopbackend.domain.Checkout;
import com.example.webshopbackend.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepositoryTest;

    @Test
    void canFindUserByUsername() {
        //given
        User userForSearch = new User(
                null,
                "Ákos",
                "Kovács",
                "kakoska",
                "kakoska@gmail.com",
                "Password123!",
                new ArrayList<>(),
                new Checkout()
        );

        userRepositoryTest.save(userForSearch);

        //when
        User userFound = userRepositoryTest.findUserByUsername(userForSearch.getUsername());

        //then
        assertThat(userFound).isEqualTo(userForSearch);
    }
}