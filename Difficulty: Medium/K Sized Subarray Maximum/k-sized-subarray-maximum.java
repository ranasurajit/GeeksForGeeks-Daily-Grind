class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) + Monotonic Deque Approach
     * 
     * TC: O(N)
     * SC: O(K)
     */
    public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        int n = arr.length;
        int i = 0; // start pointer of sliding window 
        int j = 0; // end pointer of sliding window
        ArrayList<Integer> result = new ArrayList<Integer>();
        /**
         * the intuition is to always keep the maximum within 
         * the valid sliding window at the beginning / front
         * of the data-strcuture so that we can get it in O(1)
         * time complexity and Deque is more suitable here and
         * to make the ease in sliding to next window and clean
         * up invalid indices, we can push indices in the Deque
         */
        Deque<Integer> deque = new ArrayDeque<Integer>(); // SC: O(K)
        while (j < n) { // TC: O(N)
            while (!deque.isEmpty() && arr[j] >= arr[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(j);
            if (j - i + 1 == k) {
                // sliding window size is met, so peek the maximum
                result.add(arr[deque.peekFirst()]);
                // remove computation from index 'i'
                if (i == deque.peekFirst()) {
                    // since ith index has the maximum in deque so remove it
                    deque.pollFirst();
                }
                // slide to next window
                i++;
            }
            j++;
        }
        return result;
    }
}
