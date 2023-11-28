package eif.viko.lt.bd.talpykla.eifbox.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_CREATE("admin:create"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    INSTRUCTOR_CREATE("instructor:create"),
    INSTRUCTOR_READ("instructor:read"),
    INSTRUCTOR_UPDATE("instructor:update"),
    INSTRUCTOR_DELETE("instructor:delete"),
    STUDENT_CREATE("student:create"),
    STUDENT_READ("student:read"),
    STUDENT_UPDATE("student:update"),
    STUDENT_DELETE("student:delete");

    private final String name;
}
