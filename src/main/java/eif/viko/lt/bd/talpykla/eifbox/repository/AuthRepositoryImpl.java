package eif.viko.lt.bd.talpykla.eifbox.repository;

import eif.viko.lt.bd.talpykla.eifbox.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;


@Repository
public class AuthRepositoryImpl implements AuthRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] values = {user.getName(), user.getEmail(), hashedPassword,
                user.getRole().name(), user.getGroupName(), user.getFaculty(), user.getStudyProgram()};

        int rowsAffected = jdbcTemplate.update(insertQuery, values);

        if (rowsAffected == 0) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "User with this email already exists");
        }
    }

    private int createFolder(User user) {
        int studentId = Objects.requireNonNull(jdbcTemplate.queryForObject(
                "SELECT UserID FROM Users WHERE Email = ?", Integer.class, user.getEmail()));


        String folderName = "test_" + studentId;
        return jdbcTemplate.update("INSERT INTO Folders (FolderName, StudentID) VALUES (?, ?)",
                folderName, studentId);
    }

}
