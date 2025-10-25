class Solution {
    /**
     * Approach : Using Max-Heap Approach
     * 
     * TC: O(N) + O(N / 2) ~ O(N)
     * SC: O(N)
     */
    public int minOperations(int[] arr) {
        int n = arr.length;
        PriorityQueue<Double> pq = new PriorityQueue<Double>((p, q) -> {
            return Double.compare(q, p);
        }); // SC: O(N)
        double sum = 0.0d;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += (double) arr[i];
            pq.offer((double) arr[i]);
        }
        double half = sum / 2;
        int operations = 0;
        while (sum > half) { // TC: O(N / 2)
            double current = pq.poll();
            sum -= current / 2;
            pq.offer(current / 2);
            operations++;
        }
        return operations;
    }
}
