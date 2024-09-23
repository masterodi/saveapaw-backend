package saveapaw_api.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import saveapaw_api.categories.exceptions.CategoryInvalidDataException;
import saveapaw_api.categories.exceptions.CategoryNotFoundException;
import saveapaw_api.users.exceptions.UserConflictException;
import saveapaw_api.users.exceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
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
        problemDetails.setTitle("User data is invalid");

        return problemDetails;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ProblemDetail handleCategoryNotFoundException(CategoryNotFoundException e) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetails.setTitle("Category not found");
        problemDetails.setProperty("categoryId", e.getId());

        return problemDetails;
    }

    @ExceptionHandler(CategoryInvalidDataException.class)
    public ProblemDetail handleCategoryInvalidDataException(CategoryInvalidDataException e) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetails.setTitle("Category data is invalid");

        return problemDetails;
    }
}
