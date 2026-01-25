class Solution {
    /**
     * Approach : Using Backtracking Approach
     * 
     * TC: O(N x N!) - we are generating all permutations
     * SC: O(N) - auxiliary space of recursion stack
     */
    public static ArrayList<ArrayList<Integer>> permuteDist(int[] arr) {
        int n = arr.length;
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        backtrack(0, n, arr, result);
        return result;
    }
    
    /**
     * Using Backtracking Approach
     * 
     * TC: O(N x N!) - we are generating all permutations
     * SC: O(N)
     */
    private static void backtrack(int idx, int n, int[] arr,
        ArrayList<ArrayList<Integer>> result) {
        // Base Case
        if (idx == n) {
            ArrayList<Integer> current = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                current.add(arr[j]);
            }
            result.add(current);
            return;
        }
        // Recursion Calls - we will try to swap all characters with arr[idx]
        for (int i = idx; i < n; i++) {
            swap(arr, i, idx); // modify
            backtrack(idx + 1, n, arr, result); // explore
            swap(arr, i, idx); // undo - backtrack
        }
    }
    
    /**
     * Using Enumeration Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
};
