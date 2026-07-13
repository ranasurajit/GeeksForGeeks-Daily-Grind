class Solution {
    /**
     * Approach : Using Reverse Greedy Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    public int find(int[] arr) {
        int n = arr.length;
        /**
         * if x > arr[i], then newX = x + (x - arr[i])
         * so, newX = 2 * x - arr[i]
         * 
         * if x <= arr[i], then newX = x - (arr[i] - x)
         * so, newX = 2 * x - arr[i]
         * 
         * so, need <= 2 * x - arr[i]
         * so, x >= (need + arr[i]) / 2
         * x = Ceil((need + arr[i]) / 2)
         */
        int need = 0;
        for (int i = n - 1; i >= 0; i--) { // TC : O(n)
            need = (need + arr[i] + 1) / 2;
        }
        return need;
    }
}
