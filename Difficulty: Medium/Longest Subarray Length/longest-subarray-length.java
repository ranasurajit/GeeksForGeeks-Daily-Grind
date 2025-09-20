class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public static int longestSubarray(int[] arr) {
        int n = arr.length;
        int maxLength = 0;
        int[] nge = new int[n]; // SC: O(N)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        // calculating next greater element
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        st.clear();
        // calculating previous greater element
        int[] pge = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            pge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            /**
             * we will calculate for every arr[i] to be maximum in its formed sub-array
             * i.e. towards right it can go upto j = (nge[i] - 1)
             * and towards left it can go upto i = (pge[i] + 1) so 
             * windowSize = (j - i + 1)
             */
            int windowSize = (nge[i] - 1) - (pge[i] + 1) + 1;
            // if windowSize > maximum i.e. arr[i], then it contributes to the valid sub-array
            if (windowSize >= arr[i]) {
                maxLength = Math.max(maxLength, windowSize);
            }
        }
        return maxLength;
    }
}
