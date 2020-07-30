package es.gusguma.template.microservice.user.controller;

import es.gusguma.template.microservice.user.TestConfig;
import es.gusguma.template.microservice.user.exceptions.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
class UserControllerIT {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserBusinessController userBusinessController;

    private User user;

    @BeforeEach
    void before() {
        this.user = new User("nick1", "email", new Address("country", null, null));
        this.userDao.save(user);
    }

    @Test
    void testPatchEmail() {
        UserPatchDto userPatchDto = new UserPatchDto("email", "newEmail");
        userBusinessController.patch(this.user.getId(), userPatchDto);
        assertEquals("newEmail", this.userDao.findById(user.getId()).get().getEmail());
    }

    @Test
    void testPatchCountry() {
        UserPatchDto userPatchDto = new UserPatchDto("country", "newCountry");
        userBusinessController.patch(this.user.getId(), userPatchDto);
        assertEquals("newCountry", this.userDao.findById(user.getId()).get().getAddress().getCountry());
    }

    @Test
    void testPatchException() {
        UserPatchDto userPatchDto = new UserPatchDto("invalid", "newCountry");
        assertThrows(BadRequestException.class, () -> userBusinessController.patch(user.getId(), userPatchDto));
    }

    @AfterEach
    void after() {
        this.userDao.deleteById(this.user.getId());
    }
}
