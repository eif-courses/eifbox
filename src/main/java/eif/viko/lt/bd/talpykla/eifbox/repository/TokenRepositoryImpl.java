package eif.viko.lt.bd.talpykla.eifbox.repository;

import eif.viko.lt.bd.talpykla.eifbox.model.Token;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

    @Override
    public Optional<Token> findByToken(String token) {
        return Optional.empty();
    }

    @Override
    public List<Token> findAllValidTokenByUser(Integer id) {
        return null;
    }

    @Override
    public void save(Token token) {

    }

    @Override
    public void saveAll(Iterable<Token> tokens) {

    }


    //  @Query(value = """
//      select t from Token t inner join User u\s
//      on t.user.id = u.id\s
//      where u.id = :id and (t.expired = false or t.revoked = false)\s
//      """)
//  List<Token> findAllValidTokenByUser(Integer id);
//
//  Optional<Token> findByToken(String token);
}