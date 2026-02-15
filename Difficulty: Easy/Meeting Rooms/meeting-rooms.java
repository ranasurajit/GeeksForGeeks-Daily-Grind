class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     * 
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    static boolean canAttend(int[][] arr) {
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0])); // TC: O(N x log(N))
        int lastEnd = -1;
        for (int[] time : arr) { // TC: O(N)
            int start = time[0];
            int end = time[1];
            if (start < lastEnd) {
                // current interval overlaps with last interval
                return false;
            }
            lastEnd = end;
        }
        return true;
    }
}
