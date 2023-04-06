package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private boolean status;
    private long activationKey;
}
