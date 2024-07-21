package org.example.userservice.Repositories;

import org.example.userservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository< Token,Long> {
  @Override
  Token save(Token token);
  //select * from tokens where value = <> and is_deleted = false

  Optional<Token> findByValueAndDeleted(String value, boolean isDeleted);

}
