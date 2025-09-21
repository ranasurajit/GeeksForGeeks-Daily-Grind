class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public ArrayList<Integer> calculateSpan(int[] arr) {
        int n = arr.length;
        /**
         * Intuition: Here we need to pre-compute the 
         * previous greater element which will basically
         * give the span in the left till which an element
         * is greater than it
         * 
         * so, for arr[j], if i is the index of previous 
         * greater element, so span = j - (i + 1) + 1 
         */
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        int[] pge = new int[n];                   // SC: O(N)
        for (int i = 0; i < n; i++) {             // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            pge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        ArrayList<Integer> spanList = new ArrayList<Integer>();
        for (int right = 0; right < n; right++) { // TC: O(N)
            int left = pge[right] + 1;
            // span = right - left + 1
            spanList.add(right - left + 1);
        }
        return spanList;
    }
}
