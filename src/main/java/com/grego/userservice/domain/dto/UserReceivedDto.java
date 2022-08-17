package com.grego.userservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grego.userservice.domain.Phone;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserReceivedDto {
    @NotNull(message = "name is required")
    private String name;
    @Email(message = "Email format is not valid")
    @NotNull(message = "Email is required")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{6,}$", message = "Invalid password format. Must be 6 characters or more, 1 uppercase, 1 lowercase, 1 number, 1 special character")
    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "Phone is required")
    private List<Phone> phones;


}
