
class Solution {
    /**
     * Approach : Using Hashing + Sweep Line Algorithm Approach
     * 
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N)
     */
    public static int overlapInt(int[][] arr) {
        /**
         * Intuition: We need to consider this as a timeline and we need
         * a sorted data structure that supports { key, value } pairs so
         * we need a Sorted HashMap i.e. a TreeMap and we can proceed 
         * solving this using Sweep Line Algorithm
         * 
         * (array instead of TreeMap is not so feasible as it might have
         * multiple spaces left unused and is data dependant)
         */
        TreeMap<Integer, Integer> sweepMap = new TreeMap<Integer, Integer>(); // SC: O(N)
        for (int[] interval : arr) { // TC: O(N)
            int start = interval[0];
            int end = interval[1] + 1;
            sweepMap.put(start, sweepMap.getOrDefault(start, 0) + 1); // TC: O(log(N))
            sweepMap.put(end, sweepMap.getOrDefault(end, 0) - 1);     // TC: O(log(N))
        }
        int maxOverlap = 0;
        int cumulativeSum = 0;
        for (Integer values : sweepMap.values()) { // TC: O(N)
            cumulativeSum += values;
            maxOverlap = Math.max(maxOverlap, cumulativeSum);
        }
        return maxOverlap;
    }
}
