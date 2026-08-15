class Solution {
    /**
     * Approach II : Using Digit-Based Counting Approach
     * 
     * TC : O(log(n))
     * SC : O(log(n))
     * 
     * Accepted (1113 / 1113 testcases passed)
     */
    public int countWithout(int n, int d) {
        if (n == 0) {
            return 0;
        }

        char forbidden = (char) ('0' + d);
        String s = String.valueOf(n);
        int len = s.length();

        int count = 0;
        /**
         * Count valid numbers having fewer digits than n.
         *
         * For d != 0:
         * The first digit has 8 choices because it cannot
         * be 0 and cannot be d.
         * Every remaining digit has 9 choices.
         *
         * For d == 0:
         * The first digit has 9 choices (1-9),
         * and every remaining digit has 9 choices.
         */
        for (int digits = 1; digits < len; digits++) {
            if (d == 0) {
                count += 9 * pow9(digits - 1);
            } else {
                count += 8 * pow9(digits - 1);
            }
        }
        /**
         * Count valid numbers having the same number of
         * digits as n.
         */
        for (int i = 0; i < len; i++) {
            int currentDigit = s.charAt(i) - '0';
            /**
             * Count how many valid digits smaller than
             * currentDigit can be placed at this position.
             */
            int smaller = 0;
            for (int digit = 0; digit < currentDigit; digit++) {
                /**
                 * The first digit cannot be zero.
                 */
                if (i == 0 && digit == 0) {
                    continue;
                }
                /**
                 * The forbidden digit cannot be used.
                 */
                if (digit == d) {
                    continue;
                }

                smaller++;
            }
            /**
             * Remaining positions can contain any valid digit.
             */
            count += smaller * pow9(len - i - 1);
            /**
             * If the current digit itself is forbidden,
             * we cannot continue matching n.
             */
            if (currentDigit == d) {
                return count;
            }
        }
        /**
         * n itself does not contain digit d,
         * so include n.
         */
        return count + 1;
    }
    
    /**
     * Returns 9^power.
     */
    private int pow9(int power) {
        int result = 1;

        while (power-- > 0) {
            result *= 9;
        }

        return result;
    }

    /**
     * Approach I : Using Brute-Force (Simulation) Approach
     * 
     * TC : O(n x d)
     * SC : O(1)
     * 
     * Time Limit Exceeded (1001 / 1113 testcases passed)
     */
    public int countWithoutBruteForce(int n, int d) {
        int count = 0;
        for (int i = 1; i <= n; i++) { // TC : O(n)
            int k = i;
            boolean containsD = false;
            while (k > 0) { // TC : O(9)
                int digit = k % 10; // digit
                if (digit == d) {
                    containsD = true;
                    break;
                }
                k = k / 10;
            }
            if (!containsD) {
                count++;
            }
        }
        return count;
    }
}
