package com.grego.userservice.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grego.userservice.domain.User;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhoneReceivedDto {
    @JsonIgnore
    private User user;
    @NotNull(message = "number is required")
    private String number;
    @NotNull(message = "city code is required")
    private String citycode;
    @NotNull(message = "country code is required")
    private String countrycode;

    public PhoneReceivedDto(String number, String citycode, String countrycode) {
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
    }
}
