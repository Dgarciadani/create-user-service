package com.grego.userservice.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "phones")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Phone {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "user is required")
    private User user;
    @NotNull(message = "number is required")
    private String number;
    @NotNull(message = "city code is required")
    private String citycode;
    @NotNull(message = "country code is required")
    private String countrycode;


}
