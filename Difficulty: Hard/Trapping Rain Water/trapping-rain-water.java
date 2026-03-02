class Solution {
    /**
     * Approach II : Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int maxWater(int arr[]) {
        int n = arr.length;
        /**
         * Intuition : At any index, the amount of water 
         * that can get trapped is contribution of leftMax
         * + contribution of rightMax - height of block at
         * an index
         */
        int trapped = 0;
        int i = 0;      // start pointer
        int j = n - 1;  // end pointer
        int leftMax = arr[i];
        int rightMax = arr[j];
        while (i < j) { // TC: O(N)
            leftMax = Math.max(leftMax, arr[i]);
            rightMax = Math.max(rightMax, arr[j]);
            if (leftMax < rightMax) {
                // water can be contrbuted from left index
                trapped += leftMax - arr[i];
                i++;
            } else {
                trapped += rightMax - arr[j];
                j--;
            }
        }
        return trapped;
    }

    /**
     * Approach I : Using Array Pre-Processing Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int maxWaterUsingPrefixSuffix(int arr[]) {
        int n = arr.length;
        /**
         * Intuition : At any index, the amount of water 
         * that can get trapped is contribution of leftMax
         * + contribution of rightMax - height of block at
         * an index
         */
        int[] leftMax = new int[n];        // SC: O(N)
        leftMax[0] = arr[0];
        for (int i = 1; i < n; i++) {      // TC: O(N)
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }
        int[] rightMax = new int[n];       // SC: O(N)
        rightMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
        }
        int trapped = 0;
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            trapped += Math.min(leftMax[i], rightMax[i]) - arr[i];
        }
        return trapped;
    }
}
