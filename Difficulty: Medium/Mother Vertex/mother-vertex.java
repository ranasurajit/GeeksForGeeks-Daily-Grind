class Solution {
    public int findMotherVertex(int V, int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = createGraph(edges);
        boolean[] visited = new boolean[V];
        int lastVertex = -1;
        for (int u = 0; u < V; u++) {
            if (!visited[u]) {
                dfsGraph(u, adj, visited);
                lastVertex = u;
            }
        }
        // now we will check if we start dfs from lastVertex
        visited = new boolean[V];
        dfsGraph(lastVertex, adj, visited);
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                return -1;
            }
        }
        return lastVertex;
    }
    
    private void dfsGraph(int u, Map<Integer, ArrayList<Integer>> adj,
        boolean[] visited) {
        visited[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<>())) {
            if (!visited[v]) {
                dfsGraph(v, adj, visited);
            }
        }
    }
    
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) { // TC : O(e)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }
        return adj;
    }
}
