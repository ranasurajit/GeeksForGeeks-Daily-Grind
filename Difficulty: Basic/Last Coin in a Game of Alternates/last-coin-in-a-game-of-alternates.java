class Solution {
    /**
     * Approach : Using Greedy Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    public int coin(int[] arr) {
        int n = arr.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC : O(n)
            min = Math.min(min, arr[i]);
        }
        return min;
    }
}
