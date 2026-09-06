class Solution {
    /**
     * Approach II : Using Bit-Counting Approach
     * 
     * TC : O(32 x n) ~ O(n)
     * SC : O(1)
     * 
     * Accepted (1051 /1051 testcases passed)
     */
    public long pairAndSum(int[] arr) {
        int n = arr.length;
        long sum = 0L;
        /**
         * we can check for each number which bits are set
         * and they can contribute (1 << count pair bits)
         */
        for (int bit = 0; bit < 32; bit++) { // TC : O(32)
            long countNumsWithBitSet = 0L;
            for (int num : arr) { // TC : O(n)
                if ((num & (1 << bit)) != 0) {
                    countNumsWithBitSet++;
                }
            }
            /**
             * so pairs that can be formed by such numbers
             * = countNumsWithBitSet * (countNumsWithBitSet - 1) / 2
             */
            long pairs = (countNumsWithBitSet * (countNumsWithBitSet - 1)) / 2;
            sum += pairs * (1 << bit);
        }
        return sum;
    }

    /**
     * Approach I : Using Brute-Force + Sorting Approach
     * 
     * TC : O(n²) + O(n x log(n)) ~ O(n²)
     * SC : O(1)
     * 
     * Time Limit Exceeded (1050 /1051 testcases passed)
     */
    public long pairAndSumBruteForce(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr); // TC : O(n x log(n))
        /**
         * after array 'arr' is sorted we can pair
         * any element will all elements towards it's
         * right and compute the sum
         */
        long sum = 0L;
        for (int i = 0; i < n - 1; i++) { // TC : O(n)
            for (int j = i + 1; j < n; j++) { // TC : O(n)
                sum += (arr[i] & arr[j]);
            }
        }
        return sum;
    }
}
