class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public static int getMaxArea(int arr[]) {
        int n = arr.length;
        /**
         * Intuition : We need to pre-compute next smaller
         * and previous smaller element and we need to 
         * check for every index as how much it can stretch
         * in the left and right to form rectangle
         */
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        int[] pse = getPreviousSmallerIndices(arr, n, st); // TC: O(N), SC: O(N)
        // clear Stack
        st.clear();
        int[] nse = getNextSmallerIndices(arr, n, st); // TC: O(N), SC: O(N)
        int maxArea = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            // get left index till which arr[i] can stretch towards left
            int leftIdx = pse[i] + 1;
            // get right index till which arr[i] can stretch towards right
            int rightIdx = nse[i] - 1;
            int windowSize = rightIdx - leftIdx + 1;
            maxArea = Math.max(maxArea, windowSize * arr[i]);
        }
        return maxArea;
    }
    
    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private static int[] getPreviousSmallerIndices(int[] arr, int n, Stack<Integer> st) {
        int[] pse = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            pse[i] = st.isEmpty() ? -1 : st.peek();
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
    private static int[] getNextSmallerIndices(int[] arr, int n, Stack<Integer> st) {
        int[] nse = new int[n]; // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return nse;
    }
}
