package saveapaw_api.categories;

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

import saveapaw_api.categories.exceptions.CategoryConflictException;
import saveapaw_api.categories.exceptions.CategoryNotFoundException;
import saveapaw_api.users.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService service;

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO.Query>> getMany() {
        var categories = service.getMany();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO.Query> getOne(@PathVariable String id) throws CategoryNotFoundException {
        var category = service.getOne(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("")
    public ResponseEntity<CategoryDTO.Query> create(@RequestBody CategoryDTO.Create payload)
            throws UserNotFoundException, CategoryConflictException {
        var category = service.create(payload);
        URI location = URI.create(category.id());
        return ResponseEntity.created(location).body(category);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO.Query> update(@PathVariable String id, @RequestBody CategoryDTO.Update payload)
            throws CategoryNotFoundException, CategoryConflictException {
        var category = service.update(id, payload);
        URI location = URI.create(category.id());
        return ResponseEntity.created(location).body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ProblemDetail handleUserNotFoundException(UserNotFoundException e) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetails.setTitle("User not found");

        return problemDetails;
    }

    @ExceptionHandler
    public ProblemDetail handleCategoryNotFoundException(CategoryNotFoundException e) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetails.setTitle("Category not found");

        return problemDetails;
    }

    @ExceptionHandler
    public ProblemDetail handleCategoryConflictException(CategoryConflictException e) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetails.setTitle("Category data conflict");

        return problemDetails;
    }
}
