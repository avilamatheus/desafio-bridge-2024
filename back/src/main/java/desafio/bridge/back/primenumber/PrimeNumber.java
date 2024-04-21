package desafio.bridge.back.primenumber;

import desafio.bridge.back.primenumber.exceptions.PrimeNumberException;
import java.util.Arrays;

/**
 * Class that implements operations related to prime numbers.
 */
public class PrimeNumber {

    /**
     * Maximum size of the K number used in calculations.
     */
    public final static int MAX_LIMIT = 100000000;

    /**
     * Method that calculates the number of prime positive integers n
     * less than a number k, using Sieve of Eratosthenes.
     * @param k the upper limit to find prime numbers less than
     * @return the number of prime positive integers less than k
     * @see <a href="https://educacao.uol.com.br/disciplinas/matematica/numeros-primos-veja-algoritmo-para-encontra-los.htm">Números primos - Veja algoritmo para encontrá-los - UOL Educação</a>
     * @see <a href="https://medium.com/javarevisited/sieve-of-eratosthenes-in-java-3556dcea37e2">Sieve of Eratosthenes in Java - Medium - shivam bhatele</a>
     */
    public static int numberOfPositivePrimesLessThanK(int k) throws PrimeNumberException {
        if(k <= 0) {
            return 0;
        }

        if(k > MAX_LIMIT) {
            throw new PrimeNumberException("The number entered must be less than " + MAX_LIMIT);
        }

        boolean[] isPrime = new boolean[k + 1];
        Arrays.fill(isPrime, true);

        int sum = 0;
        for (int i = 2; i <= k; i++) {
            if (isPrime[i]) {
                sum++;
                for(int j = i*2; j <= k; j+=i)
                    isPrime[j] = false;
            }
        }
        return sum;
    }

}
