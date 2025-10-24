class Solution {
    /**
     * Approach : Using Min-Heap Approach
     * 
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N)
     */
    public boolean isPossible(int[] arr, int k) {
        int n = arr.length;
        // we will store the { ending number of sub-sequence, length of sub-sequence }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> {
            if (p[0] == q[0]) {
                return p[1] - q[1];
            }
            return p[0] - q[0];
        }); // SC: O(N)
        int idx = 0;
        while (idx < n) { // TC: O(N)
            if (pq.isEmpty()) {
                pq.offer(new int[] { arr[idx], 1 }); // TC: O(log(N))
                idx++;
            } else {
                if (pq.peek()[0] == arr[idx]) {
                    // we need to start a new sub-sequence
                    pq.offer(new int[] { arr[idx], 1 }); // TC: O(log(N))
                    idx++;
                } else if (pq.peek()[0] + 1 == arr[idx]) {
                    // we can increase the sub-sequence
                    int[] prev = pq.poll();
                    pq.offer(new int[] { arr[idx], prev[1] + 1 }); // TC: O(log(N))
                    idx++;
                } else {
                    if (pq.peek()[1] < k) {
                        return false;
                    }
                    // last entry cannot be part of the sub-sequence
                    pq.poll();
                }
            }
        }
        while (!pq.isEmpty()) { // TC: O(N)
            if (pq.peek()[1] < k) {
                return false;
            }
            pq.poll();
        }
        return true;
    }
}
