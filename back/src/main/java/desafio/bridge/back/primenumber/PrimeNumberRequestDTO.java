package desafio.bridge.back.primenumber;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data transfer object (DTO) used for representing the request body in operations related to prime numbers.
 * Contains a single field for the upper limit (k) to find prime numbers less than.
 */
@Data
public class PrimeNumberRequestDTO {

    /**
     * The upper limit (k) to find prime numbers less than.
     */
    @NotNull(message = "Field 'k' is mandatory")
    private Integer k;
}
