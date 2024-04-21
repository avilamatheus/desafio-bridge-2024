package desafio.bridge.back.common.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Controller advice class responsible for handling common exceptions thrown by controllers in the application.
 */
@ControllerAdvice
public class ApiCommonExceptionHandler {

    /**
     * Handles exceptions of type {@link NoResourceFoundException}.
     * @param exception the exception to be handled
     * @return {@link ResponseEntity} with HTTP status code 404 (Not Found) and a body containing the exception message
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoResourceFound(NoResourceFoundException exception) {

        return new ResponseEntity<>(new ErrorResponseDTO(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exceptions of type {@link HttpRequestMethodNotSupportedException}.
     * @param exception the exception to be handled
     * @return {@link ResponseEntity} with HTTP status code 405 (Method Not Allowed) and a body containing the exception message
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ErrorResponseDTO(exception.getMessage()));
    }

    /**
     * Handles generic exceptions that are not specifically handled by other methods in this class.
     * @param exception the exception to be handled
     * @return {@link ResponseEntity} with HTTP status code 500 (Internal Server Error) and a body containing the exception message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> genericExceptionHandler(Exception exception) {
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO(exception.getMessage()));
    }

}
