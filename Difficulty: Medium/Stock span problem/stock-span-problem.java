class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public ArrayList<Integer> calculateSpan(int[] arr) {
        int n = arr.length;
        // we need to find the previous greater element
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        int[] result = new int[n];    // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            result[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        ArrayList<Integer> span = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            /**
             * result[i] + 1 is the index next to previous greater element so number 
             * of elements in span is current index (i) - (result[i] + 1) + 1
             * which is the window of the span
             */
            span.add(i - (result[i] + 1) + 1);
        }
        return span;
    }
}
