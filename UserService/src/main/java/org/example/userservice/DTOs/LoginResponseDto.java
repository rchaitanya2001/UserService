package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.Models.Token;

@Getter
@Setter
public class LoginResponseDto {
    private Token token;
}
