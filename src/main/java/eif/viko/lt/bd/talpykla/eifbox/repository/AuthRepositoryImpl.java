package eif.viko.lt.bd.talpykla.eifbox.repository;

import eif.viko.lt.bd.talpykla.eifbox.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Repository
public class AuthRepositoryImpl implements AuthRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AuthRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public int register(User user) {
        registerUser(user);
        return createFolder(user);
    }

    private void registerUser(User user) {
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
    }

    private int createFolder(User user) {
        Integer userId = namedParameterJdbcTemplate.queryForObject(
                "SELECT UserID FROM Users WHERE Email = :email",
                Collections.singletonMap("email", user.getEmail()),
                Integer.class);

        if (userId == null) {
            throw new RuntimeException("User not found");
        }

        String folderName = "test_" + userId;

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("folderName", folderName)
                .addValue("userId", userId);

        return namedParameterJdbcTemplate.update(
                "INSERT INTO Folders (FolderName, StudentID) VALUES (:folderName, :userId)",
                parameters);
    }
}
