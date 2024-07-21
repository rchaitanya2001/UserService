package org.example.userservice.Repositories;

import org.example.userservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository< Token,Long> {

  //Token save(Token token);
  //select * from tokens where value = <> and is_deleted = false

  Optional<Token> findByValueAndIsDeleted(String value, boolean isDeleted);

}
