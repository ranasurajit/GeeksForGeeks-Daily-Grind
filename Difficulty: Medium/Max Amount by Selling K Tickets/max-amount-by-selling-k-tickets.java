class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach : Using Greedy + PriorityQueue (Max-Heap) Approach
     * 
     * TC : O(n x log(n) + Min(n, k) x log(n)) ~ O(n x log(n)
     * SC : O(n)
     */
    public int maxAmount(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<Long> pq =
            new PriorityQueue<>((p, q) -> Long.compare(q, p)); // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            pq.offer((long) arr[i]);  // TC : O(log(n))
        }
        long value = 0L;
        while (!pq.isEmpty() && k > 0) { // TC : O(Min(n, k))
            long current = pq.poll();
            value += current;
            if (current > 1) {
                pq.offer(current - 1);  // TC : O(log(n))
            }
            k--;
        }
        return (int) (value % MOD);
    }
}
