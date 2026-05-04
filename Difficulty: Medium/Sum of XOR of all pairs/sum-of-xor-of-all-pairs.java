class Solution {
    /**
     * Approach II : Using Optimal (Permutation + Bit-Manipulation) Approach
     * 
     * TC : O(32 x n) ~ O(n)
     * SC : O(1)
     * 
     * Accepted (1112 / 1112 testcases passed)
     */
    public long sumXOR(int[] arr) {
        int n = arr.length;
        long sum = 0L;
        for (int bit = 0; bit < 32; bit++) { // TC : O(32)
            long count1s = 0L; // count how many numbers have bit set
            for (int x : arr) { // TC : O(n)
                if ((x & 1 << bit) != 0) {
                    count1s++;
                }
            }
            long count0s = n - count1s;
            // contribution by current bit to the total
            sum += (count1s * count0s) * (1L << bit);
        }
        return sum;
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC : O(n²)
     * SC : O(1)
     * 
     * Time limit exceeded (1110 / 1112 testcases passed)
     */
    public long sumXORBruteForce(int[] arr) {
        int n = arr.length;
        long sum = 0L;
        for (int i = 0; i < n; i++) { // TC : O(n)
            for (int j = i; j < n; j++) { // TC : O(n)
                sum += (arr[i] ^ arr[j]);
            }
        }
        return sum;
    }
}
