package desafio.bridge.back.primenumber;

/**
 * Data transfer object (DTO) used to represent response data for the calculation of prime numbers.
 * Contains the result count of prime numbers and the elapsed time for the calculation.
 */
public record PrimeNumberResponseDTO(int result, String timeElapsed) {
}
