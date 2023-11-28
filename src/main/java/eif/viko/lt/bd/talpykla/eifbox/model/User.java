package eif.viko.lt.bd.talpykla.eifbox.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@ToString
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public enum Role {Administrator, Moderator, Student, Reviewer}
    private int userID;
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email must be valid")
    private String email;
    private String password;
    private Role role;
    private String groupName;
    private String faculty;
    private String studyProgram;
}
