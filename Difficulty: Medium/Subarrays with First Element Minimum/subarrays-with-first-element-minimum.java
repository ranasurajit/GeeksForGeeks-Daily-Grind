class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int countSubarrays(int[] arr) {
        int n = arr.length;
        /**
         * we will be storing the next smaller element
         * index in the Stack (Monotonic Stack)
         */
        Stack<Integer> st = new Stack<>(); // SC: O(N)
        int[] nse = new int[n]; // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        /**
         * we need to find how much element at an index can
         * be expanded (to form sub-array) till we find
         * a smaller element
         */
        int count = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            count += (nse[i] - i);
        }
        return count;
    }
}
