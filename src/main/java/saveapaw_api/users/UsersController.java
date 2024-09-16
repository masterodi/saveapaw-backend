package saveapaw_api.users;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO.Query>> getMany() {
        var users = service.getMany();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO.Query> getOne(@PathVariable String id) {
        try {
            var user = service.getOne(id);
            return ResponseEntity.ok(user);
        } catch (IllegalAccessError e) {
            return ResponseEntity.notFound().location(URI.create(id)).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<UserDTO.Query> create(@RequestBody UserDTO.Create payload) {
        var user = service.create(payload);
        URI location = URI.create(user.id());
        return ResponseEntity.created(location).body(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO.Query> update(@PathVariable String id, @RequestBody UserDTO.Update payload) {
        var user = service.update(id, payload);
        URI location = URI.create(user.id());
        return ResponseEntity.created(location).body(user);
    }

    // TODO
    // @PatchMapping("")
    // public ResponseEntity<Object> updateMany(@RequestBody dynamic payload) {
    // if (payload.op().equals("update")) {
    // var users = service.updateMany(payload.data());
    // return ResponseEntity.ok(users);
    // }
    // if (payload.op().equals("delete")) {
    // service.deleteManyById(payload.data());
    // return ResponseEntity.noContent().build();
    // }
    // }

    // @PatchMapping("")
    // public void deleteManyById(@RequestBody UserDTO. payload) {
    // if (payload.op().equals("remove")) {
    // service.deleteManyById(payload.ids());
    // }
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
