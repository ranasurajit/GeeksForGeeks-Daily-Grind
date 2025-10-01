class Solution {
    /**
     * Approach II :  Using Recursion + Backtracking Approach
     * 
     * TC: O(N x K) where K = unique permutations ~ N! ~ O(N x N!)
     * SC: O(N x K) + O(N)
     */
    public static ArrayList<ArrayList<Integer>> uniquePerms(int[] arr) {
        int n = arr.length;
        /**
         * Sorting array 'arr' as the order of elements of array 'arr' 
         * does not matter here. This will handle Lexical sort and
         * avoid duplicates without the necessity of a HashSet
         */
        Arrays.sort(arr);
        boolean[] used = new boolean[n];
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        backtrack(n, arr, used, new ArrayList<Integer>(), result); // TC: O(N x K), SC: O(N)
        return result;
    }
    
    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(N x K) where K = unique permutations ~ N! ~ O(N x N!)
     * SC: O(N)
     */
    private static void backtrack(int n, int[] arr, boolean[] used,
        ArrayList<Integer> current, ArrayList<ArrayList<Integer>> result) {
        // Base Case
        if (current.size() == n) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        // Recursion Calls
        for (int i = 0; i < n; i++) {
            if (used[i]) {
                continue;
            }
            // skip duplicates
            if (i > 0 && arr[i] == arr[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            current.add(arr[i]); // modify
            backtrack(n, arr, used, current, result); // explore
            // backtrack
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }

    /**
     * Approach I :  Using Recursion + Backtracking Approach
     * 
     * TC: O(N x N! x log(N!))
     * SC: O(N x N!)
     */
    public static ArrayList<ArrayList<Integer>> uniquePermsBruteForce(int[] arr) {
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
        // Base Case
        if (current.size() == n) {
            set.add(new ArrayList<Integer>(current));
            return;
        }
        // Recursion Calls
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
}
