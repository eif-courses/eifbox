package eif.viko.lt.bd.talpykla.eifbox.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public enum Role {Administrator, Moderator, Student, Reviewer}
    private int userID;
    private String name;
    private String email;
    private String password;
    private Role role;
    private String groupName;
    private String faculty;
    private String studyProgram;
}
