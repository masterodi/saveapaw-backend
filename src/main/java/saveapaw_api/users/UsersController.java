package saveapaw_api.users;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import saveapaw_api.users.exceptions.UserConflictException;
import saveapaw_api.users.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService service;

    @GetMapping("")
    public ResponseEntity<List<UserDTO.Query>> getMany() {
        var users = service.getMany();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO.Query> getOne(@PathVariable String id) throws UserNotFoundException {
        var user = service.getOne(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<UserDTO.Query> create(@RequestBody UserDTO.Create payload) throws UserConflictException {
        var user = service.create(payload);
        URI location = URI.create(user.id());
        return ResponseEntity.created(location).body(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO.Query> update(@PathVariable String id, @RequestBody UserDTO.Update payload)
            throws UserNotFoundException, UserConflictException {
        var user = service.update(id, payload);
        URI location = URI.create(user.id());
        return ResponseEntity.created(location).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFoundException(UserNotFoundException e) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetails.setTitle("User not found");
        problemDetails.setProperty("userId", e.getId());

        return problemDetails;
    }

    @ExceptionHandler(UserConflictException.class)
    public ProblemDetail handleUserConflictException(UserConflictException e) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetails.setTitle("User data conflict");

        return problemDetails;
    }
}
