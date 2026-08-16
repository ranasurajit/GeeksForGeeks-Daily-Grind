class Solution {
    /**
     * Approach : Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     * - O(n) - recursion stack
     */
    public int minProd(int[] arr) {
        int n = arr.length;
        return solve(0, n, arr)[0];
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2ⁿ)
     * SC : O(n)
     */
    private int[] solve(int idx, int n, int[] arr) {
        // Base Case
        if (idx == n - 1) {
            return new int[] { arr[idx], arr[idx] };
        }
        // Recursion Calls
        // we can try to pick or skip element at index 'idx'
        int[] skip = solve(idx + 1, n, arr);
        int x = arr[idx];
        int pickWithMin = x * skip[0];
        int pickWithMax = x * skip[1];
        int pickMin = Math.min(x, 
            Math.min(pickWithMin, pickWithMax));
        int pickMax = Math.max(x,
            Math.max(pickWithMin, pickWithMax));
        int minProduct = Math.min(skip[0], pickMin);
        int maxProduct = Math.max(skip[1], pickMax);
        return new int[] { minProduct, maxProduct };
    }
}
