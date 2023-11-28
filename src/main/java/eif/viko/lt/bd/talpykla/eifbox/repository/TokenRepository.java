package eif.viko.lt.bd.talpykla.eifbox.repository;

import eif.viko.lt.bd.talpykla.eifbox.model.Token;
import eif.viko.lt.bd.talpykla.eifbox.user.User;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {

    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);

    void save(Token token);

    void saveAll(Iterable<Token> tokens);
    //tokenRepository.saveAll(validUserTokens);


//  @Query(value = """
//      select t from Token t inner join User u\s
//      on t.user.id = u.id\s
//      where u.id = :id and (t.expired = false or t.revoked = false)\s
//      """)
//  List<Token> findAllValidTokenByUser(Integer id);
//
//  Optional<Token> findByToken(String token);
}