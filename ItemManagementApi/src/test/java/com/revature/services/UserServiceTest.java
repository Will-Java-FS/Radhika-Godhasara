package com.revature.services;

import com.revature.Model.User;
import com.revature.Respository.UserRepository;
import com.revature.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;


import static org.mockito.Mockito.when;

@SpringBootTest(classes = com.revature.Application.class)
public class UserServiceTest {

    @Autowired
    UserService us;

    @MockBean
    UserRepository ur;

    @Test
    void register(){
        User u = new User(0L, "user1", "password1", "John", "Jones", false, null);

        when(ur.save(u)).thenReturn(new User(1L, "user1", "password1", "John", "Jones", false, null));

        u = us.register(u);

        Assertions.assertEquals(1, u.getUserID());
        Assertions.assertEquals("user1",u.getUserName());
    }

    @Test
    void login() {
        User u = new User(1L, "user1", "password1", "John", "Jones", false, null);

        Mockito.when(ur.findByUserName(u.getUserName())).thenReturn(u);

        User actual = us.login(u.getUserName());
        Assertions.assertEquals(u.getUserName(), actual.getUserName());
        Assertions.assertEquals(u.getPassword(), actual.getPassword());
    }

    @Test
    public void getUsers(){
        List<User> users = ur.findAll();
        User u = new User(1L, "user1", "password1", "John", "Jones", false, null);
        users.add(u);

        Assertions.assertFalse(users.isEmpty());
    }
}
