package org.example.userservice.Controllers;

import org.example.userservice.DTOs.*;
import org.example.userservice.Exceptions.InvalidPasswordException;
import org.example.userservice.Exceptions.InvalidTokenException;
import org.example.userservice.Models.Token;
import org.example.userservice.Models.User;
import org.example.userservice.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    UserController(UserService userService)
    {
        this.userService = userService;
    }
    @PostMapping("/signup")//localhost :8080/users/signup
    public UserDto signUp(@RequestBody SignUpRequestDto requestDto)
    {
        User user = userService.signUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );
        //get userdto from user
        return UserDto.from(user);
    }
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto) throws InvalidPasswordException {
       Token token = userService.login(requestDto.getEmail(),requestDto.getPassword());
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setToken(token);
        return responseDto;

    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(LogOutRequestDto requestDto) throws InvalidTokenException {
       ResponseEntity<Void> responseEntity = null;
        try
       {
           userService.logout(requestDto.getToken());
           responseEntity = new ResponseEntity<>(HttpStatus.OK);
       }
       catch(Exception e)
       {
           System.out.println("Something went wrong");
           responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

        return responseEntity;
    }

}
