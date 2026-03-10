class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public int sumSubMins(int[] arr) {
        int n = arr.length;
        /**
         * Intuition is to find the contribution count
         * of arr[i] which can become the minimum element
         * of every possible sub-array
         * 
         * so we need to find contribution of arr[i]
         * = arr[i] x (nse[i] - i) * (i - pse[i]) where,
         * nse = nextSmallerElementIndex and
         * pse = previousSmallerElementIndex i.e.
         * for every arr[i] we need to find the number 
         * sub-arrays to left * number of sub-array to right
         * arr[i] will be the minimum element
         */
        Stack<Integer> st = new Stack<>(); // SC: O(N)
        int[] nse = nextSmallerIndex(arr, n, st);     // TC: O(N), SC: O(N)
        st.clear();
        int[] pse = previousSmallerIndex(arr, n, st); // TC: O(N), SC: O(N)
        long count = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            count += (long) arr[i] * nse[i] * pse[i];
        }
        return (int) count;
    }
    
    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int[] previousSmallerIndex(int[] arr, int n, Stack<Integer> st) {
        int[] pse = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }
            pse[i] = st.isEmpty() ? (i + 1) : i - st.peek();
            st.push(i);
        }
        return pse;
    }
    
    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int[] nextSmallerIndex(int[] arr, int n, Stack<Integer> st) {
        int[] nse = new int[n]; // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n - i : st.peek() - i;
            st.push(i);
        }
        return nse;
    }
}
