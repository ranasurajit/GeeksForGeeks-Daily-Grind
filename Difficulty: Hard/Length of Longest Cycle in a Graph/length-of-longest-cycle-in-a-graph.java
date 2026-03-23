class Solution {
    private int maxLength = -1;

    /**
     * Approach : Using DFS on Directed Graph Approach
     * 
     * TC: O(E) + O(2 x V + E) ~ O(V + E)
     * SC: O(V + E) + O(V) + O(V) + O(V) ~ O(V + E)
     */
    public int longestCycle(int V, int[][] edges) {
        // we will create the adjacency list for the graph
        Map<Integer, ArrayList<Integer>> adj =
            createGraph(edges); // TC: O(E), SC: O(V + E)
        // creating visited array and another array to track if in current recursion
        boolean[] visited = new boolean[V]; // SC: O(V)
        boolean[] inRecursion = new boolean[V]; // SC: O(V)
        // this will compute the graph cycle length
        int[] depth = new int[V]; // SC: O(V)
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (!visited[i]) {
                depth[i] = 0; // reset depth at every dfs start
                dfsGraph(adj, i, visited, inRecursion, depth); // TC: O(V + E), SC: O(V)
            }
        }
        return maxLength;
    }
    
    /**
     * Using DFS on Directed Graph Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private void dfsGraph(Map<Integer, ArrayList<Integer>> adj, int u,
        boolean[] visited, boolean[] inRecursion, int[] depth) {
        visited[u] = true;
        inRecursion[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<>())) {
            if (!visited[v]) {
                depth[v] = depth[u] + 1;
                dfsGraph(adj, v, visited, inRecursion, depth);
            } else if (inRecursion[v]) {
                // found cycle here
                maxLength = Math.max(maxLength, depth[u] - depth[v] + 1);
            }
        }
        inRecursion[u] = false;
    }
    
    /**
     * Using Hashing Approach
     * 
     * TC: O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>(); // SC: O(V + E)
        for (int[] edge : edges) { // TC: O(E)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }
        return adj;
    }
}
