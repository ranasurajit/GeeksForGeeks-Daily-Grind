
class Solution {
    /**
     * Approach : Using Hashing + Sweep Line Algorithm Approach
     * 
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N)
     */
    public static int overlapInt(int[][] arr) {
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
