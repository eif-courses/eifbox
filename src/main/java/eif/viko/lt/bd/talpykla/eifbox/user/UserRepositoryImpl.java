package eif.viko.lt.bd.talpykla.eifbox.user;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        // TODO Hash the password
        String hashedPassword = "labs"; // hashPassword(user.getPassword());

        String insertQuery = "INSERT IGNORE INTO Users (Name, Email, Password, Role, GroupName, Faculty, StudyProgram) " +
                "VALUES (:name, :email, :password, :role, :groupName, :faculty, :studyProgram)";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", hashedPassword)
                .addValue("role", user.getRole().name())
                .addValue("groupName", user.getGroupName())
                .addValue("faculty", user.getFaculty())
                .addValue("studyProgram", user.getStudyProgram());
        int rowsAffected = namedParameterJdbcTemplate.update(insertQuery, parameters);

        if (rowsAffected == 0) {
            throw new RuntimeException("User with this email already exists!");
        }
        return user;
    }

}
