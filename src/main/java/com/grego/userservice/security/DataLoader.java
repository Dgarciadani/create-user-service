package com.grego.userservice.security;


import com.grego.userservice.domain.dto.PhoneReceivedDto;
import com.grego.userservice.domain.dto.UserReceivedDto;
import com.grego.userservice.service.IUserService;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private IUserService userService;

    public DataLoader(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder1 = new BCryptPasswordEncoder();
        String password1 = bCryptPasswordEncoder1.encode("password");

        userService.create(new UserReceivedDto("Grego1", "Gregogc1@gmail.com", password1, List.of(new PhoneReceivedDto("23434", "232", "54"))));

    }
}
