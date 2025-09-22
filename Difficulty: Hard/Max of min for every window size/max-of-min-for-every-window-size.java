class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(5 x N) ~ O(N)
     * SC: O(4 x N) ~ O(N)
     */
    public ArrayList<Integer> maxOfMins(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> maxMinList = new ArrayList<Integer>();
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        int[] pse = getPreviousSmallerElementIndex(arr, n, st); // TC: O(N), SC: O(N)
        st.clear();
        int[] nse = getNextSmallerElementIndex(arr, n, st);     // TC: O(N), SC: O(N)
        /**
         * Intuition is to store the spans/window in which arr[i]
         * can be the minimum among all elements
         */
        int[] span = new int[n + 1];  // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int right = nse[i] - 1;
            int left = pse[i] + 1;
            int windowSize = right - left + 1;
            /**
             * window size is the index where arr[i] is the 
             * minimum element so, we need to store maximum 
             * of such minimums for every window sizes
             */
            span[windowSize] = Math.max(span[windowSize], arr[i]);
        }
        // we need to fill the missing window sizes too
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            span[i] = Math.max(span[i], span[i + 1]);
        }
        for (int i = 1; i <= n; i++) {     // TC: O(N)
            maxMinList.add(span[i]);
        }
        return maxMinList;
    }
    
    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int[] getPreviousSmallerElementIndex(int[] arr, int n, Stack<Integer> st) {
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
    private int[] getNextSmallerElementIndex(int[] arr, int n, Stack<Integer> st) {
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

    /**
     * Approach I : Using Sliding Window Approach
     * 
     * TC: O(N x N)
     * SC: O(N) - reused
     * 
     * Time Limit Exceeded (1010 / 1115 testcases passed)
     */
    public ArrayList<Integer> maxOfMinsBruteForce(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> maxMinList = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            maxMinList.add(maxMinInWindow(arr, n, i + 1)); // TC: O(N), SC: O(N)
        }
        return maxMinList;
    }

    /**
     * Using Sliding Window Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int maxMinInWindow(int[] arr, int n, int k) {
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int maxValue = Integer.MIN_VALUE;
        Deque<Integer> deque = new ArrayDeque<Integer>(); // SC: O(N)
        while (j < n) { // TC: O(N)
            while (!deque.isEmpty() && arr[deque.peekLast()] >= arr[j]) {
                deque.pollLast();
            }
            deque.offerLast(j);
            if (i > deque.peekFirst()) {
                deque.pollFirst();
            }
            if (j - i + 1 == k) {
                maxValue = Math.max(maxValue, arr[deque.peekFirst()]);
                // move to next sliding window
                i++;
            }
            j++;
        }
        return maxValue;
    }
}
