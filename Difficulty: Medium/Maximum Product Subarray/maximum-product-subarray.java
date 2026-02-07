class Solution {
    /**
     * Approach II : Using Array Prefix-Suffix (Optimal) Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     * 
     * Accepted (1112 / 1112 testcases passed)
     */
    int maxProduct(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n]; // SC: O(N)
        prefix[0] = arr[0];
        int[] suffix = new int[n]; // SC: O(N)
        suffix[n - 1] = arr[n - 1];
        for (int i = 1; i < n; i++) {       // TC: O(N)
            prefix[i] = (prefix[i - 1] == 0 ? 1 : prefix[i - 1]) * arr[i];
            int j = n - i - 1;
            suffix[j] = (suffix[j + 1] == 0 ? 1 : suffix[j + 1]) * arr[j];
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {       // TC: O(N)
            result = Math.max(result, Math.max(prefix[i], suffix[i]));
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC: O(N²)
     * SC: O(1)
     * 
     * Time Limit Exceeded (1110 / 1112 testcases passed)
     */
    int maxProductBruteForce(int[] arr) {
        int n = arr.length;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {     // TC: O(N)
            int currentProduct = 1;
            for (int j = i; j < n; j++) { // TC: O(N)
                currentProduct = currentProduct * arr[j];
                result = Math.max(result, currentProduct);
            }
        }
        return result;
    }
}
