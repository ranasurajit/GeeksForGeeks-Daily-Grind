class Solution {
    /**
     * Approach : Using Prefix Array Approach
     * 
     * TC : O(n) + O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    public int bitonic(int[] arr) {
        int n = arr.length;
        // we will process from left to right
        int[] prefix = new int[n];    // SC : O(n)
        Arrays.fill(prefix, 1);
        for (int i = 1; i < n; i++) { // TC : O(n)
            if (arr[i] >= arr[i - 1]) {
                prefix[i] = 1 + prefix[i - 1];
            }
        }
        // we will process from right to left
        int[] suffix = new int[n];         // SC : O(n)
        Arrays.fill(suffix, 1);
        for (int i = n - 2; i >= 0; i--) { // TC : O(n)
            if (arr[i] >= arr[i + 1]) {
                suffix[i] = 1 + suffix[i + 1];
            }
        }
        int maxLength = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            maxLength = Math.max(maxLength, prefix[i] + suffix[i] - 1);
        }
        return maxLength;
    }
}
