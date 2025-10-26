class Solution {
    /**
     * Approach : Using Min-Heap(PriorityQueue) Approach
     * 
     * TC: O(N x log(N)) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N)
     */
    public static int minCost(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // SC: O(N)
        for (int x : arr) { // TC: O(N)
            pq.offer(x);    // TC: O(log(N))
        }
        int totalCost = 0;
        while (pq.size() > 1) { // TC: O(N)
            int first = pq.poll();
            int second = 0;
            if (!pq.isEmpty()) {
                second = pq.poll();
            }
            int cost = first + second;
            pq.offer(cost); // TC: O(log(N))
            totalCost += cost;
        }
        return totalCost;
    }
}
