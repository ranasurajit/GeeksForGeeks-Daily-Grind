class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) + Deque Approach
     * 
     * TC: O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public ArrayList<Integer> longestSubarray(int[] arr, int x) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int n = arr.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Deque<Integer> minDeque = new ArrayDeque<Integer>(); // SC: O(N)
        Deque<Integer> maxDeque = new ArrayDeque<Integer>(); // SC: O(N)
        int maxLength = 0;
        int start = 0;
        int end = 0;
        while (j < n) { // TC: O(N)
            while (!minDeque.isEmpty() && arr[j] < arr[minDeque.peekLast()]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(j);
            while (!maxDeque.isEmpty() && arr[j] > arr[maxDeque.peekLast()]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(j);
            while (arr[maxDeque.peekFirst()] - arr[minDeque.peekFirst()] > x) {
                // remove computation from index 'i'
                if (minDeque.peekFirst() == i) {
                    minDeque.pollFirst();
                }
                if (maxDeque.peekFirst() == i) {
                    maxDeque.pollFirst();
                }
                i++;
            }
            if (maxLength < j - i + 1) {
                maxLength = j - i + 1;
                start = i;
                end = j;
            }
            j++;
        }
        for (i = start; i <= end; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
