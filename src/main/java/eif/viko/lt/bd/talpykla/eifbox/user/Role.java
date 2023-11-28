package eif.viko.lt.bd.talpykla.eifbox.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor

public enum Role {
    ADMIN(Set.of(
            Permission.ADMIN_CREATE,
            Permission.ADMIN_READ,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE,
            Permission.INSTRUCTOR_CREATE,
            Permission.INSTRUCTOR_READ,
            Permission.INSTRUCTOR_UPDATE,
            Permission.INSTRUCTOR_DELETE,
            Permission.STUDENT_CREATE,
            Permission.STUDENT_READ,
            Permission.STUDENT_UPDATE,
            Permission.STUDENT_DELETE)),
    INSTRUCTOR(Set.of(
            Permission.INSTRUCTOR_CREATE,
            Permission.INSTRUCTOR_READ,
            Permission.INSTRUCTOR_UPDATE,
            Permission.INSTRUCTOR_DELETE,
            Permission.STUDENT_CREATE,
            Permission.STUDENT_READ,
            Permission.STUDENT_UPDATE,
            Permission.STUDENT_DELETE
    )),
    STUDENT(Set.of(
            Permission.STUDENT_CREATE,
            Permission.STUDENT_READ,
            Permission.STUDENT_UPDATE,
            Permission.STUDENT_DELETE
    )),
    USER(Collections.emptySet());

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }


}
