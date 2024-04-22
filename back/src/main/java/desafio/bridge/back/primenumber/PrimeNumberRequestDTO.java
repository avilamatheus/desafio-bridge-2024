package desafio.bridge.back.primenumber;

import jakarta.validation.constraints.NotBlank;

/**
 * Data transfer object (DTO) used for representing the request body in operations related to prime numbers.
 * Contains a single field for the upper limit (k) to find prime numbers less than.
 */
public class PrimeNumberRequestDTO {

    /**
     * The upper limit (k) to find prime numbers less than.
     */
    @NotBlank(message = "Field 'k' is mandatory")
    private String k;

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }
}
