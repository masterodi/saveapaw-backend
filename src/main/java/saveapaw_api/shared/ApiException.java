package saveapaw_api.shared;

public abstract class ApiException extends Exception {
    public ApiException(String message) {
        super(message);
    }
}
