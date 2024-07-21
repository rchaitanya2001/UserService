package org.example.userservice.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration//this class is a special class which is going to have a some methods with @Bean annotation
//whatever beans we are creating here will be stored inside springcontext file
public class Configurations {
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
