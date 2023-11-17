package eif.viko.lt.bd.talpykla.eifbox.controller;

import eif.viko.lt.bd.talpykla.eifbox.model.User;
import eif.viko.lt.bd.talpykla.eifbox.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthRepository repository;

    @Autowired
    public AuthController(AuthRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register_new_user(@RequestBody User user){
        repository.register(user);
    }
}
