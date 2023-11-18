package eif.viko.lt.bd.talpykla.eifbox.controller;

import eif.viko.lt.bd.talpykla.eifbox.model.User;
import eif.viko.lt.bd.talpykla.eifbox.repository.AuthRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Auth", description = "User auth endpoint")
@RequestMapping("api/v1/auth")
@Validated
public class AuthController {

    private final AuthRepository repository;

    @Autowired
    public AuthController(AuthRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register_new_user(@RequestBody @Valid User user) {
        repository.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }
}
