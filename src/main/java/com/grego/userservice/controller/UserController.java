package com.grego.userservice.controller;

import com.grego.userservice.domain.dto.UserReceivedDto;
import com.grego.userservice.domain.dto.UserSendDto;
import com.grego.userservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")

public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserSendDto> register(@RequestBody @Valid UserReceivedDto userDto) {
        return new ResponseEntity<UserSendDto>(userService.create(userDto), HttpStatus.CREATED);

    }

    @GetMapping("/find")
    public UserSendDto findById(@RequestParam UUID id) {
        return userService.findById(id);
    }


}
