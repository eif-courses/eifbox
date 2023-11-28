package eif.viko.lt.bd.talpykla.eifbox.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;


@Validated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

   // public enum Role {Administrator, Moderator, Student, Reviewer}
    private Integer id;
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
