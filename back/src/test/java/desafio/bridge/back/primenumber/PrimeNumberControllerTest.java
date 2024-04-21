package desafio.bridge.back.primenumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import desafio.bridge.back.primenumber.exceptions.PrimeNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Test class for {@link PrimeNumberController}.
 * @see <a href="https://spring.io/guides/gs/testing-web">Testing the Web Layer - Spring</a>
 * @see <a href="https://howtodoinjava.com/spring-boot2/testing/rest-controller-unit-test-example/">Test a Spring Boot REST Controller with JUnit 5 - HowToDoInJava</a>
 * @see <a href="https://stackoverflow.com/a/63920995">Mock Static Methods in JUnit5 using PowerMockito</a>
 */
@WebMvcTest(PrimeNumberController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PrimeNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests POST request with correct input.
     * Should return HTTP 200 and the result.
     */
    @Test
    void testPostWithCorrectInput_ShouldReturnHttp200AndResult() throws Exception {
        int kValue = 100;
        int expectedReturnValue = 25;
        PrimeNumberRequestDTO requestDTO = new PrimeNumberRequestDTO();
        requestDTO.setK(kValue);

        try (MockedStatic<PrimeNumber> mockedPrimeNumber = Mockito.mockStatic(PrimeNumber.class)) {
            mockedPrimeNumber.when(() -> PrimeNumber.numberOfPositivePrimesLessThanK(kValue)).thenReturn(expectedReturnValue);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/primenumber")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(requestDTO)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(expectedReturnValue))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.timeElapsed").isNotEmpty());

            mockedPrimeNumber.verify(() -> PrimeNumber.numberOfPositivePrimesLessThanK(kValue), Mockito.times(1));

        }
    }

    /**
     * Tests POST request with parameter 'k' exceeding the maximum limit.
     * Should return HTTP 400 with a specific error message.
     */
    @Test
    void testPostWithParameterKExceedingLimit_ShouldReturnBadRequest() throws Exception {
        int limit = PrimeNumber.MAX_LIMIT;
        String expectedErrorMsg = "The number entered must be less than " + limit;
        PrimeNumberRequestDTO requestDTO = new PrimeNumberRequestDTO();
        requestDTO.setK(limit + 1);

        try (MockedStatic<PrimeNumber> mockedPrimeNumber = Mockito.mockStatic(PrimeNumber.class)) {
            mockedPrimeNumber.when(() -> PrimeNumber.numberOfPositivePrimesLessThanK(limit + 1))
                    .thenThrow(new PrimeNumberException(expectedErrorMsg));

            mockMvc.perform(MockMvcRequestBuilders.post("/api/primenumber")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(requestDTO)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value(expectedErrorMsg));

            mockedPrimeNumber.verify(() -> PrimeNumber.numberOfPositivePrimesLessThanK(limit + 1), Mockito.times(1));
        }
    }

    /**
     * Tests POST request without parameter 'k'.
     * Should return HTTP 400 with a specific error message.
     */
    @Test
    void testPostWithoutParameterK_ShouldReturnBadRequest() throws Exception {
        String expectedErrorMsg = "Input validation error";
        String expectedError = "Field 'k' is mandatory";

        try (MockedStatic<PrimeNumber> mockedPrimeNumber = Mockito.mockStatic(PrimeNumber.class)) {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/primenumber")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value(expectedErrorMsg))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value(expectedError));

            mockedPrimeNumber.verify(() -> PrimeNumber.numberOfPositivePrimesLessThanK(0), Mockito.times(0));
        }
    }

    /**
     * Tests POST request with invalid parameter 'k'.
     * Should return HTTP 400 with a specific error message.
     */
    @Test
    void testPostWithInvalidParameterK_ShouldReturnBadRequest() throws Exception {
        String expectedErrorMsg = "Error processing the request. Please check if the sent data is correct and try again.";
        String invalidJson = """
        {
            "k": "laboratorio-bridge"
        }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/primenumber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value(expectedErrorMsg));

    }

    /**
     * Tests HTTP method not allowed.
     * Should return HTTP 405.
     */
    @Test
    void testNotAllowedHttpMethod_ShouldReturnHttp405() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/primenumber")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").isNotEmpty());
    }
}

