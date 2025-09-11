class Solution {
    /**
     * Approach : Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            // already at the last element
            return 0;
        }
        if (arr[0] == 0) {
            // cannot move further
            return -1;
        }
        int low = 0;
        int high = 0;
        int jumps = 0;
        while (high < n - 1) { // TC: O(N)
            int farthest = high;
            for (int i = low; i <= high; i++) {
                farthest = Math.max(farthest, i + arr[i]);
            }
            if (farthest == high) {
                // cannot move further → unreachable
                return -1;
            }
            low = high + 1;
            high = farthest;
            jumps++;
        }
        return jumps;
    }
}
