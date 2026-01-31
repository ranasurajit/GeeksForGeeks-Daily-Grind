class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) + Deque Approach
     * 
     * TC: O(N)
     * SC: O(K)
     */
    public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        int n = arr.length;
        /**
         * we need a data-structure to push and pop from both ends in O(1)
         * time complexity. so we would try to always keep the maximum
         * value in the front of the sliding window
         * 
         * to easily remove any item from invalid window, we will push
         * index of element in Deque instead of pushing the element
         */
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Deque<Integer> deque = new ArrayDeque<Integer>(); // SC: O(K)
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (j < n) { // TC: O(N)
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[j]) {
                deque.pollLast();
            }
            deque.offerLast(j);
            if (j - i + 1 == k) {
                result.add(arr[deque.peekFirst()]);
                // remove computation from index i
                if (deque.peekFirst() == i) {
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
