class Solution {
    /**
     * Approach:  Using Recursion + Backtracking Approach
     * 
     * TC: O(N x N! x log(N!))
     * SC: O(N x N!)
     */
    public static ArrayList<ArrayList<Integer>> uniquePerms(int[] arr) {
        int n = arr.length;
        ArrayList<ArrayList<Integer>> result;
        boolean[] used = new boolean[n];
        Set<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        solveRecursion(n, arr, used, new ArrayList<Integer>(), set);
        result = new ArrayList<ArrayList<Integer>>(set);
        Collections.sort(result, (a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i) != b.get(i)) {
                    return a.get(i) - b.get(i);
                }
            }
            return 0;
        });
        return result;
    }
    
    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(N x N!)
     * SC: O(N x N!)
     */
    private static void solveRecursion(int n, int[] arr, boolean[] used,
        ArrayList<Integer> current, Set<ArrayList<Integer>> set) {
        if (current.size() == n) {
            set.add(new ArrayList<Integer>(current));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            current.add(arr[i]); // modify
            solveRecursion(n, arr, used, current, set); // explore
            // backtrack
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
};
