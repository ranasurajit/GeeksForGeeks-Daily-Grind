class Solution {
    /**
     * Approach II : Using Math + Modulo Approach
     * 
     * TC : O(|b|) + O(k) ~ O(|b|)
     * SC : O(k)
     * 
     * where k = log(mod)
     */
    public int getLastDigit(String a, String b) {
        long lastDigit = (long) (a.charAt(a.length() - 1) - '0');
        /**
         * If exponent is 1234 then we can split it as
         * 1 * 1000 + 2 * 100 + 3 * 10  + 4 i.e.
         * ((1 * 10 + 2) * 10 + 3) * 10 + 4
         * 
         * cycle for 
         * 0: [0],
         * 1: [1],
         * 2: [2, 4, 8, 6],
         * 3: [3, 9, 7, 1],
         * 4: [4, 6],
         * 5: [5],
         * 6: [6],
         * 7: [7, 9, 3, 1]
         * 8: [8, 4, 2, 6]
         * 9: [9, 1]
         * 
         * so maximum value of cycle is 4
         */
        long mod = computeMod(b, 4); // TC : O(|b|), SC : O(1)
        if (mod == 0) {
            mod = 4L;
        }
        return (int) fastPower(lastDigit, mod) % 10; // TC : O(k), SC : O(k) 
    }
    
    /**
     * Using Math Simulation Approach
     * 
     * TC : O(|b|)
     * SC : O(1)
     */
    private long computeMod(String b, int mod) {
        long result = 0;
        for (char ch : b.toCharArray()) { // TC : O(|b|)
            result = (result * 10 + (ch - '0')) % mod;
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force (Binary Exponentiation) Approach
     * 
     * TC : O(log(b))
     * SC : O(log(b))
     * 
     * Overflows for larger values of String a and b
     */
    public int getLastDigitBruteForce(String a, String b) {
        long numA = Long.valueOf(a) % 10;
        long numB = Long.valueOf(b);
        long pow = fastPower(numA, numB); // TC : O(log(b)), SC : O(log(b))
        return (int) (pow % 10);
    }
    
    /**
     * Using Binary Exponentiation Approach
     * 
     * TC : O(log(b))
     * SC : O(log(b))
     */
    private long fastPower(long a, long b) {
        if (b == 0L) {
            return 1L;
        }
        if (b == 1L) {
            return a;
        }
        long half = fastPower(a, b / 2);
        long ans = half * half;
        if ((b & 1) != 0) {
            // exponent is odd
            return a * ans;
        }
        return ans;
    }
};
