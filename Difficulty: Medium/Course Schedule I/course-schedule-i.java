class Solution {
    /**
     * Approach : Using DFS Directed Graph Approach
     * 
     * TC: O(E) + O(2 x V + E) ~ O(V + E)
     * SC: O(V + E) + O(V) + O(V) ~ O(V + E)
     */
    public boolean canFinish(int n, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> adj =
            createGraph(prerequisites); // TC: O(E), SC: O(V + E)
        /**
         * Intuition: It will be possible to complete all
         * tasks if and only if there is no cycle present
         */
        boolean[] visited = new boolean[n];     // SC: O(V)
        boolean[] inRecursion = new boolean[n]; // SC: O(V)
        for (int i = 0; i < n; i++) {       // TC: O(V)
            if (!visited[i] &&
                dfsGraphHasCycle(i, visited, inRecursion, adj)) { // TC: O(V + E)
                // graph has cycle
                return false;
            }
        }
        // graph has no cycle so, we can complete all tasks
        return true;
    }
    
    /**
     * Using DFS Directed Graph Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private boolean dfsGraphHasCycle(int u, boolean[] visited, boolean[] inRecursion,
        Map<Integer, ArrayList<Integer>> adj) {
        visited[u] = true;
        inRecursion[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<>())) {
            if (!visited[v] && dfsGraphHasCycle(v, visited, inRecursion, adj)) {
                // detected cycle
                return true;
            } else if (inRecursion[v]) {
                // detected cycle
                return true;
            }
        }
        inRecursion[u] = false;
        return false;
    }
    
    /**
     * Using Hashing Approach
     * 
     * TC: O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) { // TC: O(E)
            adj.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        return adj;
    }
}
