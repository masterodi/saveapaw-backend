package saveapaw_api.categories.exceptions;

import saveapaw_api.shared.ApiException;

public abstract class CategoryException extends ApiException {
    public CategoryException(String message) {
        super(message);
    }
}