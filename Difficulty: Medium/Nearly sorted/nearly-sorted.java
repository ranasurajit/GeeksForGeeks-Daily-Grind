class Solution {
    /**
     * Approach : Using Min-Heap (PriorityQueue) Approach
     * 
     * TC: O(K x log(K)) + O((N - K) x log(K)) + O(K x log(K)) ~ O(N x log(K))
     * SC: O(K)
     */
    public void nearlySorted(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // SC: O(K)
        for (int i = 0; i <= k; i++) { // TC: O(K)
            pq.offer(arr[i]); // TC: O(log(K))
        }
        int j = 0; // pointer index to swap the original value with sorted value
        for (int i = k + 1; i < n; i++) { // TC: O(N - K)
            arr[j++] = pq.poll();
            pq.offer(arr[i]); // TC: O(log(K))
        }
        // place all the remaining elements from PriorityQueue to array 'arr'
        while (!pq.isEmpty()) { // TC: O(K)
            arr[j++] = pq.poll(); // TC: O(log(K))
        }
    }
}
