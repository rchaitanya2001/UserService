package org.example.userservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel{
    private String value;
    @ManyToOne//users can have multiple tokens for diff sessions
    private User user;
    private Date expiryAt;
}
