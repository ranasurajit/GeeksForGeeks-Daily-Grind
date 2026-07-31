class Solution {
    /**
     * Approach : Using Difference Array Approach
     * 
     * TC : O(m) + O(n) ~ O(m + n)
     * SC : O(n)
     */
    public int findMax(int n, int[] a, int[] b, int[] k) {
        int m = k.length;
        long[] arr = new long[n];     // SC : O(n)
        // array 'arr' is by default filled with value '0'
        for (int i = 0; i < m; i++) { // TC : O(m)
            int left = a[i];
            int right = b[i];
            arr[left] += k[i];
            if (right + 1 < n) {
                arr[right + 1] -= k[i];
            }
        }
        long cumSum = (long) arr[0];
        long maxValue = (long) arr[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            cumSum += arr[i];
            maxValue = Math.max(maxValue, cumSum);
        }
        return (int) maxValue;
    }
}
