package saveapaw_api.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO.Login payload) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/register")
    public ResponseEntity register() {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/logout")
    public ResponseEntity logout() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/session")
    public ResponseEntity getSession() {
        throw new UnsupportedOperationException();
    }
}
