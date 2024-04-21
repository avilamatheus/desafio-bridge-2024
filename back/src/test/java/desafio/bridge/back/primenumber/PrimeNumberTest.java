package desafio.bridge.back.primenumber;

import desafio.bridge.back.primenumber.exceptions.PrimeNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link PrimeNumber}.
 */
class PrimeNumberTest {

    /**
     * Tests {@link PrimeNumber#numberOfPositivePrimesLessThanK(int)} with negative input.
     * Should return zero.
     */
    @Test
    void numberOfPositivePrimesWithNegativeInput_shouldReturnZero() {
        assertEquals(0, PrimeNumber.numberOfPositivePrimesLessThanK(-100));
    }

    /**
     * Tests {@link PrimeNumber#numberOfPositivePrimesLessThanK(int)} with input exceeding the maximum limit.
     * Should throw {@link PrimeNumberException}.
     */
    @Test
    void numberOfPositivePrimesWithInputExceedingMaxLimit_shouldThrowPrimeNumberException() {
        assertThrows(PrimeNumberException.class, () ->
                PrimeNumber.numberOfPositivePrimesLessThanK(PrimeNumber.MAX_LIMIT + 1));
    }

    /**
     * Tests {@link PrimeNumber#numberOfPositivePrimesLessThanK(int)} with input 100.
     * Should return 25.
     */
    @Test
    void numberOfPositivePrimesLessThan100_shouldReturn25() {
        assertEquals(25, PrimeNumber.numberOfPositivePrimesLessThanK(100));
    }

    /**
     * Tests {@link PrimeNumber#numberOfPositivePrimesLessThanK(int)} with input 10.
     * Should return 4.
     */
    @Test
    void numberOfPositivePrimesLessThan10_shouldReturn4() {
        assertEquals(4, PrimeNumber.numberOfPositivePrimesLessThanK(10));
    }

}
