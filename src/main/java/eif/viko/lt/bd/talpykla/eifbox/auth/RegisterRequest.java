package eif.viko.lt.bd.talpykla.eifbox.auth;

import eif.viko.lt.bd.talpykla.eifbox.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String name;
  private String email;
  private String password;
  private Role role;
  private String faculty;
  private String groupName;
  private String studyProgram;
}
