class Solution {
    /**
     * Approach : Using Max-Heap Approach
     * 
     * TC: O(N x log(K)) + O(K x log(K)) ~ O(N x log(K))
     * SC: O(K)
     */
    public ArrayList<ArrayList<Integer>> kClosest(int[][] points, int k) {
        /**
         * We will compute for every points teh distance from origin (0, 0) as
         * x ^ 2 + y ^ 2 and add it to the Min-Heap
         */
        PriorityQueue<long[]> pq = 
            new PriorityQueue<long[]>((p, q) -> Long.compare(q[0], p[0])); // SC: O(K)
        for (int[] point : points) { // TC: O(N)
            long distanceSq = ((long) point[0] * (long) point[0]) + 
                ((long) point[1] * (long) point[1]);
            if (pq.isEmpty() || pq.size() < k) {
                pq.offer(new long[] { distanceSq, point[0], point[1] }); // TC: O(log(K))
            } else {
                if (distanceSq < pq.peek()[0]) {
                    pq.poll();
                    pq.offer(new long[] { distanceSq, point[0], point[1] }); // TC: O(log(K))
                }
            }
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        while (!pq.isEmpty()) { // TC: O(K)
            ArrayList<Integer> point = new ArrayList<Integer>();
            long[] current = pq.poll(); // TC: O(log(K))
            point.add((int) current[1]);
            point.add((int) current[2]);
            result.add(point);
        }
        return result;
    }
}
