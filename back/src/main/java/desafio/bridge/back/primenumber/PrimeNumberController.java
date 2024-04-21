package desafio.bridge.back.primenumber;

import desafio.bridge.back.common.api.ErrorResponseDTO;
import desafio.bridge.back.primenumber.exceptions.PrimeNumberException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StopWatch;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Controller class responsible for handling requests related to prime numbers.
 */
@RestController
@RequestMapping("/api/primenumber")
@Validated
public class PrimeNumberController {

    /**
     * Calculates the number of prime positive integers less than a given number k.
     * @param primeNumberRequestDTO DTO containing the upper limit (k) to find prime numbers less than
     * @return {@link ResponseEntity} containing the count of prime positive integers less than k
     * and the time elapsed to conclude the request
     */
    @PostMapping("")
    public ResponseEntity<PrimeNumberResponseDTO> numberOfPrimesLessThanK(@RequestBody @Valid PrimeNumberRequestDTO primeNumberRequestDTO) {
        int k = primeNumberRequestDTO.getK();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int result = PrimeNumber.numberOfPositivePrimesLessThanK(k);
        stopWatch.stop();

        DecimalFormat df = new DecimalFormat("#.######");
        String elapsedTimeInSeconds = df.format(stopWatch.getTotalTimeSeconds());

        return new ResponseEntity<>(new PrimeNumberResponseDTO(result, elapsedTimeInSeconds), HttpStatus.OK);
    }

    /**
     * Handles exceptions of type {@link PrimeNumberException}
     * @param exception the exception to be handled
     * @return {@link ResponseEntity} with HTTP status code 400 (Bad Request) and a body containing the exception message
     */
    @ExceptionHandler(PrimeNumberException.class)
    public ResponseEntity<ErrorResponseDTO> handlePrimeNumberException(PrimeNumberException exception) {
        return new ResponseEntity<>(new ErrorResponseDTO(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions of type {@link MethodArgumentNotValidException}.
     * @param exception the exception to be handled
     * @return {@link ResponseEntity} with HTTP status code 400 (Bad Request) and a body
     * containing the error message and a list of validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });

        return new ResponseEntity<>(new ErrorResponseDTO("Input validation error", errors), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions of type {@link HttpMessageNotReadableException}.
     * @param exception the exception to be handled
     * @return {@link ResponseEntity} with HTTP status code 400 (Bad Request) and a body containing the exception message
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(new ErrorResponseDTO("Error processing the request. Please check if the sent data is correct and try again."), HttpStatus.BAD_REQUEST);
    }



}