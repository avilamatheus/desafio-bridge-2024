package desafio.bridge.back.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 * Data transfer object (DTO) used to represent error responses in API exception handling.
 * Contains fields for the error message and a list of errors, if applicable.
 * @param errorMessage Error message
 * @param errors List of errors
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseDTO(String errorMessage, List<String> errors) {

    /**
     * Constructs an ErrorResponseDTO with the provided error message.
     * @param errorMessage The error message describing the encountered issue.
     */
    public ErrorResponseDTO(String errorMessage) {
        this(errorMessage, null);
    }
}
