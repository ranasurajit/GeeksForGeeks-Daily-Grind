class Solution {
    /**
     * Approach II : Using Optimal (Recursion) Approach
     * 
     * TC : O(n)
     * SC : O(log(n))
     */
    public int binarySearchable(int[] arr) {
        int n = arr.length;
        return solve(0, n - 1, arr, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(n)
     * SC : O(log(n))
     */
    private int solve(int left, int right, int[] arr,
        int minAllowed, int maxAllowed) {
        // Base Case
        if (left > right) {
            return 0;
        }
        // Recursion
        int count = 0;
        int mid = left + (right - left) / 2;
        if (arr[mid] > minAllowed && arr[mid] < maxAllowed) {
            count = 1;
        }
        count += solve(left, mid - 1, arr,
            minAllowed, Math.min(maxAllowed, arr[mid]));
        count += solve(mid + 1, right, arr,
            Math.max(minAllowed, arr[mid]), maxAllowed);
        return count;
    }

    /**
     * Approach I : Using Brute-Force (Binary Search) Approach
     * 
     * TC : O(n x log(n))
     * SC : O(1)
     */
    public int binarySearchableBruteForce(int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            int target = arr[i];
            int idx = findTarget(arr, n, target); // TC : O(log(n)), SC : O(1)
            if (idx >= 0 && idx < n) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Using Binary Search Approach
     * 
     * TC : O(log(n))
     * SC : O(1)
     */
    private int findTarget(int[] arr, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
};
