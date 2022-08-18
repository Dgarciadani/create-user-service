package com.grego.userservice;

import com.grego.userservice.domain.Phone;
import com.grego.userservice.domain.User;
import com.grego.userservice.domain.dto.PhoneReceivedDto;
import com.grego.userservice.domain.dto.UserReceivedDto;
import com.grego.userservice.domain.dto.UserSendDto;
import com.grego.userservice.service.impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private UserService userService;


    @Autowired
    private ModelMapper modelmapper;


	@Test
	void createUser(){
		UserReceivedDto userDto = new UserReceivedDto();
		userDto.setName("John");
		userDto.setEmail("pablito@gmail.com");
		userDto.setPassword("Humberto1@");
		userDto.setPhones(List.of(new PhoneReceivedDto("+569", "912345678","484")));
		UserSendDto userCreated = userService.create(userDto);
		assertNotNull(userCreated);
		assertNotNull(userCreated.getId());
		System.out.println(userCreated.toString());
	}

	@Test
	void createUserWithOutSomeField(){
		try{
			UserReceivedDto userDto = new UserReceivedDto();
			//with out name
			//userDto.setName("John");
			userDto.setEmail("pablito@gmail.com");
			userDto.setPassword("Humberto1@");
			userDto.setPhones(List.of(new PhoneReceivedDto("+569", "912345678","484")));
			UserSendDto userCreated = userService.create(userDto);
			Assertions.fail("Should not be able to create user without name");
			//this test case pass because the validation is in the dto class
		}catch(Exception e){
			Assertions.assertEquals("Field 'name' is required", e.getMessage());
		}
	}

	@Test
	void createUserDuplicate(){
		try {
			this.createUser();
			this.createUser();
		}
		catch (Exception e) {
			assertEquals("Email already registered",e.getMessage());
		}
	}

	@Test
	void findUserByEmail() {
		String mail = "pablito@gmail.com";
		this.createUser();
		UserSendDto user = userService.findUserByEmail(mail);
		assertNotNull(user);
		assertNotNull(user.getId());
		assertTrue(user.isIsactive());
	}

	@Test
	void disableUser(){
		this.createUser();
		userService.disableUser("pablito@gmail.com");
		assertFalse(userService.findUserByEmail("pablito@gmail.com").isIsactive());
	}

}
