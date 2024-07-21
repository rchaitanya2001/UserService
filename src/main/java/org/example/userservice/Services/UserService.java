package org.example.userservice.Services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.userservice.Exceptions.InvalidPasswordException;
import org.example.userservice.Exceptions.InvalidTokenException;
import org.example.userservice.Models.Token;
import org.example.userservice.Models.User;
import org.example.userservice.Repositories.TokenRepository;
import org.example.userservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private UserRepository userRepository;

    private TokenRepository tokenRepository;
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }
    public User signUp(String email,String password,String name){
        //controller will get the user objecct from service,
        //controller will convert user object into Dto,
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent())
        {
            //user is already present in the DB
            return optionalUser.get();
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);

    }
    public Token login(String email,String password) throws InvalidPasswordException {
        /*
        1.check if the user exists with the given email or not
        2.If not, throw an exception or redirect the user to signup
        3.If yes, then compare the incoming pwd with the pwd stored in the db
        4.If pwd matches then login successful and return the new token
         */
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            //user with given email isn't present in db
            return null;
        }
        User user = optionalUser.get();
        if(bCryptPasswordEncoder.matches(password, user.getHashedPassword()))
        {
          //throw an exception
            throw new InvalidPasswordException("Please Enter Correct Pasword");
        }
        //login succesful,generate a new token
        Token token = generateToken(user);
        //save token to database
        return tokenRepository.save(token);
        //return null;
    }
    private Token generateToken(User user)
    {
        LocalDate currentTime = LocalDate.now();//current time
        LocalDate thirtyDaysFromCurrentTime = currentTime.plusDays(30);
        Date expiryDate = Date.from(thirtyDaysFromCurrentTime.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Token token = new Token();
        token.setExpiryAt(expiryDate);
        //token value is a randomly generated String of 128 characters
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        token.setUser(user);
        return token;
    }

    public void logout(String tokenValue) throws InvalidTokenException
    {
    //validate if the given token is present in the DB as well as is_deleted = false
       Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeleted(tokenValue,
              false );
       if(optionalToken.isEmpty())
       {
         //throw an exception
        throw new InvalidTokenException("Invalid token passed");
       }
       Token token = optionalToken.get();
       token.setDeleted(true);
       token.isDeleted();
       tokenRepository.save(token);
    }


}
