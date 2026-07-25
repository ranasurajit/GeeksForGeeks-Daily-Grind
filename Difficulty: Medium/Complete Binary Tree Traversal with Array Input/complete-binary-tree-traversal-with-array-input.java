class Solution {
    private int maxLevel = 0;

    /**
     * Approach : Using DFS Approach
     * 
     * TC : O(h) + O(h) + O(h² x log(h)) ~ O(h² x log(h))
     * SC : O(n) + O(h) ~ O(n)
     */
    public ArrayList<ArrayList<Integer>> levelSort(int[] arr) {
        int n = arr.length;
        Map<Integer, ArrayList<Integer>> levelMap = new HashMap<>(); // SC : O(n)
        dfsTree(0, n, 0, arr, levelMap); // TC : O(h), SC : O(h)
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int level = 0; level <= maxLevel; level++) { // TC : O(h)
            ArrayList<Integer> children = levelMap.get(level);
            Collections.sort(children); // TC : O(h x log(h))
            result.add(children);
        }
        return result;
    }

    /**
     * Using DFS Approach
     * 
     * TC : O(h)
     * SC : O(h)
     */
    private void dfsTree(int idx, int n, int level, int[] arr,
        Map<Integer, ArrayList<Integer>> levelMap) {
        // Base Case
        if (idx >= n) {
            return;
        }
        // Recursion Calls
        levelMap.computeIfAbsent(level, k -> new ArrayList<>()).add(arr[idx]);
        maxLevel = Math.max(maxLevel, level);
        dfsTree(2 * idx + 1, n, level + 1, arr, levelMap);
        dfsTree(2 * idx + 2, n, level + 1, arr, levelMap);
    }
}
