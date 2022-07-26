package com.sanalyapp.sanaly.repository;

import com.sanalyapp.sanaly.model.AppUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    AppUser appUser = new AppUser(111L, "John", "Sina", "Qwerty1!", "ggg@gmail.com", "+77077470707", null, null);

    @Test
    void checkExistsByEmail() {
        appUserRepository.save(appUser);
        boolean expected = appUserRepository.existsByEmail(appUser.getEmail());
        assertThat(expected).isTrue();
    }
    @Test
    void checkNotExistsByEmail() {
        boolean expected = appUserRepository.existsByEmail(appUser.getEmail());
        assertThat(expected).isFalse();
    }

    @Test
    void checkGetAppUserById() {
        Long ID = appUser.getId();
        appUserRepository.save(appUser);
        AppUser expected = appUserRepository.getAppUserById(ID);
//        System.out.println("Checking stuff ..... " + appUser.toString());
        assertSame(expected, appUser);
    }

    @Test
    void checkGetByEmail() {
        String email = appUser.getEmail();
        appUserRepository.save(appUser);
        AppUser expected = appUserRepository.getByEmail(email);
//        System.out.println("Checking stuff ..... " + appUser.toString());
        assertSame(expected, appUser);
    }
}