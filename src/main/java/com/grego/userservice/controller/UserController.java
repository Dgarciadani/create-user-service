package com.grego.userservice.controller;

import com.grego.userservice.domain.dto.UserReceivedDto;
import com.grego.userservice.domain.dto.UserSendDto;
import com.grego.userservice.service.IUserService;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserSendDto> register(@RequestBody @Valid UserReceivedDto userDto) {
        return new ResponseEntity<UserSendDto>(userService.create(userDto), HttpStatus.CREATED);

    }

    //this endpoint is protected by JWT whit any authority level
    //use Bearer token in the Authorization header
    @PutMapping("/disable")
    public ResponseEntity<UserSendDto> disableUser(@RequestParam String email)   {
        LOGGER.info("Disabling user with email: %s" + (email));
        return new ResponseEntity<UserSendDto>(userService.disableUser(email), HttpStatus.OK);
    }


}
