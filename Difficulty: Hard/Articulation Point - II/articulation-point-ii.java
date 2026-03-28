class Solution {
    private static int counter = 1;

    /**
     * Approach : Using Tarjan's Algorithm (DFS Graph Traversal) Approach
     * 
     * TC: O(E) + O(2 x V + E) + O(V) ~ O(V + E)
     * SC: O(V + E) + O(4 x V) ~ O(V + E)
     */
    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = 
            createGraph(edges); // TC: O(E), SC: O(V + E)
        boolean[] visited = new boolean[V]; // SC: O(V)
        int[] dis = new int[V]; // SC: O(V)
        int[] low = new int[V]; // SC: O(V)
        int[] art = new int[V]; // SC: O(V)
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (!visited[i]) {
                dfsGraph(i, -1, adj, visited,
                    dis, low, art); // TC: O(V + E), SC: O(V)
            }
        }
        ArrayList<Integer> artPoints = new ArrayList<>();
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (art[i] == 1) {
                artPoints.add(i);
            }
        }
        if (artPoints.isEmpty()) {
            artPoints.add(-1);
        }
        return artPoints;
    }
    
    /**
     * Using DFS Graph Traversal Approach
     * 
     * TC: O(V + 2 x E) ~ O(V + E)
     * SC: O(V)
     */
    private static void dfsGraph(int u, int parent,
        Map<Integer, ArrayList<Integer>> adj, boolean[] visited,
        int[] dis, int[] low, int[] art) {
        visited[u] = true;
        dis[u] = counter;
        low[u] = counter++;
        int child = 0;
        for (Integer v : adj.getOrDefault(u, new ArrayList<>())) {
            if (v == parent) {
                // we are not supposed to go back to parent node 
                continue;
            } else if (!visited[v]) {
                // node 'v' is not visited via some path
                // we can continue performing DFS from node v
                dfsGraph(v, u, adj, visited, dis, low, art);
                // once dfs is done we need to update low
                low[u] = Math.min(low[u], low[v]);
                // articulation array update
                if (low[v] >= dis[u] && parent != -1) {
                    art[u] = 1;
                }
                child++;
            } else {
                // node 'v' is visited via some path so update low
                low[u] = Math.min(low[u], dis[v]);
            }
        }
        if (parent == -1 && child > 1) {
            art[u] = 1;
        }
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(2 x E) ~ O(E)
     * SC: O(V + E)
     */
    private static Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        return adj;
    }
}
