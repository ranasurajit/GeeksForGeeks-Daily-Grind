class Solution {
    /**
     * Approach II : Using Binary Search + Math Approach
     * 
     * TC: O(log(N) Base 2 x log(M) Base 10)
     * SC: O(1)
     * 
     * Accepted (1120 / 1120 testcases passed)
     */
    public int getCount(int n, int d) {
        int low = 1;
        int high = n;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            int diff = mid - sumOfDigits(mid); // TC: O(log(M) Base 10)
            if (diff >= d) {
                /**
                 * mid - sum(mid) >= d so we need to find
                 * minimum mid satisfying the condition
                 */
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        /**
         * low is the value which satisfies the minimum value to
         * satisfy the equation so count = n - low + 1
         */
        return n - low + 1;
    }

    /**
     * Approach I : Using Math Approach
     * 
     * TC: O(N x log(M) Base 10)
     * SC: O(1)
     * 
     * Time Limit Exceeded (1010 / 1120 testcases passed)
     */
    public int getCountBruteForce(int n, int d) {
        int count = 0;
        for (int i = 1; i <= n; i++) { // TC: O(N)
            int diff = i - sumOfDigits(i); // TC: O(log(M) Base 10)
            if (diff >= d) {
                count++;
            }
        }
        return count;
    }

    /**
     * Using Math Approach
     * 
     * TC: O(log(M) Base 10)
     * SC: O(1)
     */
    private int sumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            int rem = num % 10;
            sum += rem;
            num = num / 10;
        }
        return sum;
    }
};
