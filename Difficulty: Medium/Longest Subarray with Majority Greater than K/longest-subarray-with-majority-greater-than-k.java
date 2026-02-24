class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int longestSubarray(int[] arr, int k) {
        int n = arr.length;
        /**
         * Intuition: we can replace elements > k with +1 and 
         * elements <= k with -1
         */
        for (int i = 0; i < n; i++) {      // TC: O(N)
            arr[i] = arr[i] > k ? 1 : -1;
        }
        /**
         * building prefix array
         */
        int[] prefix = new int[n];         // SC: O(N)
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {      // TC: O(N)
            prefix[i] = prefix[i - 1] + arr[i];
        }
        // we will store indices in Stack to form Monotonic Decreasing Stack
        Stack<Integer> st = new Stack<>(); // SC: O(N)
        for (int i = 0; i < n; i++) {      // TC: O(N)
            if (st.isEmpty() || prefix[i] < prefix[st.peek()]) {
                st.push(i);
            }
        }
        // now traverse from right to left to find the prefix[r] > prefix[l - 1]
        int maxLength = 0;
        for (int r = n - 1; r >= 0; r--) {  // TC: O(N)
            if (prefix[r] > 0) {
                maxLength = Math.max(maxLength, r + 1);
            }
            while (!st.isEmpty() && prefix[r] > prefix[st.peek()]) {
                maxLength = Math.max(maxLength, r - st.peek());
                st.pop();
            }
        }
        return maxLength;
    }
}
