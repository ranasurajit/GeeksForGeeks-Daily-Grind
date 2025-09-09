class Solution {
    /**
     * Approach : Using Sorting + Simulation Approach
     * 
     * TC: O(N x log(N)) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int assignHole(int[] mices, int[] holes) {
        int n = holes.length;
        Arrays.sort(mices); // TC: O(N x log(N))
        Arrays.sort(holes); // TC: O(N x log(N))
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int diff = Math.abs(mices[i] - holes[i]);
            maxDiff = Math.max(maxDiff, diff);
        }
        return maxDiff;
    }
};
