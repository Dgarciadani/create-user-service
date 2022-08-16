package com.grego.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Phone> phones;

    //MetaData
    private LocalDate createAt;
    private LocalDate updateAt;

    //state
    private boolean active;



}
