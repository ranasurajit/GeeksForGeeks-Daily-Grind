class Solution {
    /**
     * Approach I : Using Max-Heap Approach
     * 
     * TC: O(M x log(K)) + O(N x log(K)) ~ O((M + N) x log(K))
     * SC: O(K)
     */
    public int kthElement(int a[], int b[], int k) {
        PriorityQueue<Integer> pq =
            new PriorityQueue<Integer>((p, q) -> q - p); // SC: O(K)
        for (int x : a) {        // TC: O(M)
            if (pq.size() < k) {
                pq.offer(x);     // TC: O(log(K))
            } else {
                if (x < pq.peek()) {
                    pq.poll();
                    pq.offer(x); // TC: O(log(K))
                }
            }
        }
        for (int x : b) {        // TC: O(N)
            if (pq.size() < k) {
                pq.offer(x);
            } else {
                if (x < pq.peek()) {
                    pq.poll();
                    pq.offer(x); // TC: O(log(K))
                }
            }
        }
        return pq.peek();
    }
}
