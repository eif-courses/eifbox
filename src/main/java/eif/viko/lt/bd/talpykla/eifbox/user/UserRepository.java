package eif.viko.lt.bd.talpykla.eifbox.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
}