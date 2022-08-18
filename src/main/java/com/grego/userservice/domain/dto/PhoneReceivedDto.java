package com.grego.userservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grego.userservice.domain.User;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhoneReceivedDto {

    private User user;
    @NotNull(message = "number is required")
    private String number;
    @NotNull(message = "city code is required")
    private String citycode;
    @NotNull(message = "country code is required")
    private String countrycode;

}
