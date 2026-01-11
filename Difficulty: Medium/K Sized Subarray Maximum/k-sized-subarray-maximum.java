class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) Approach
     * 
     * TC: O(N)
     * SC: O(K)
     */
    public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        int n = arr.length;
        int i = 0; // pointer at the start of sliding window
        int j = 0; // pointer at the end of sliding window
        // we will try to keep maximum always at the front of Deque
        Deque<Integer> deque = new ArrayDeque<Integer>(); // SC: O(K)
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (j < n) { // TC: O(N)
            while (!deque.isEmpty() && arr[j] >= arr[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(j);
            if (j - i + 1 == k) {
                result.add(arr[deque.peekFirst()]);
                // remove computation from index 'i'
                if (i == deque.peekFirst()) {
                    deque.pollFirst();
                }
                i++;
            }
            j++;
        }
        return result;
    }
}
