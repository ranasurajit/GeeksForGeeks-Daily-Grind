class Solution {
    /**
     * Approach : Using Min-Heap Approach
     * 
     * TC: O(N x log(N) + K x log(N))
     * SC: O(N)
     */
    public ArrayList<ArrayList<Integer>> kClosest(int[][] points, int k) {
        /**
         * We will compute for every points teh distance from origin (0, 0) as
         * x ^ 2 + y ^ 2 and add it to the Min-Heap
         */
        PriorityQueue<long[]> pq = 
            new PriorityQueue<long[]>(Comparator.comparingLong(a -> a[0])); // SC: O(N)
        for (int[] point : points) { // TC: O(N)
            long distanceSq = ((long) point[0] * (long) point[0]) + 
                ((long) point[1] * (long) point[1]);
            pq.offer(new long[] { distanceSq, point[0], point[1] }); // TC: O(log(N))
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        while (k-- > 0) { // TC: O(K)
            ArrayList<Integer> point = new ArrayList<Integer>();
            long[] current = pq.poll(); // TC: O(log(N))
            point.add((int) current[1]);
            point.add((int) current[2]);
            result.add(point);
        }
        return result;
    }
}
