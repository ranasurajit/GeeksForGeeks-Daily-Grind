class Solution {
    /**
     * Approach I : Using Max-Heap Approach
     * 
     * TC: O(N² x log(K))
     * SC: O(K)
     */
    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length; // this is a square matrix
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((p, q) -> q - p); // SC: O(K)
        for (int i = 0; i < n; i++) {     // TC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (pq.size() < k) {
                    pq.offer(mat[i][j]);  // TC: O(log(K))
                } else {
                    if (pq.peek() > mat[i][j]) {
                        pq.poll();
                        pq.offer(mat[i][j]); // TC: O(log(K))
                    }
                }
            }
        }
        return pq.peek();
    }
}
