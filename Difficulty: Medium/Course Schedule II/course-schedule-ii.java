class Solution {
    /**
     * Approach : Using Topological Sort (DFS) Approach
     * 
     * TC: O(E) + O(V + E) + O(2 x V + E) + O(V) ~ O(V + E)
     * SC: O(V + E) + O(V) + O(V) + O(V) ~ O(V + E)
     */
    public ArrayList<Integer> findOrder(int n, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> adj =
            createGraph(prerequisites); // TC: O(E), SC: O(V + E)
        boolean graphHasCycle = hasCycleDFS(n, adj); // TC: O(V + E), SC: O(V)
        ArrayList<Integer> order = new ArrayList<Integer>();
        if (graphHasCycle) {
            /**
             * if graph has cycle then no need to go further 
             * as topological sort is possible for DAGs only
             */
            return order;
        }
        Stack<Integer> st = new Stack<Integer>(); // SC: O(V)
        boolean[] visited = new boolean[n]; // SC: O(V)
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (!visited[i]) {
                dfsGraph(i, adj, st, visited); // TC: O(V + E), SC: O(V)
            }
        }
        while (!st.isEmpty()) { // TC: O(V)
            order.add(st.pop());
        }
        return order;
    }
    
    /**
     * Using DFS Approach
     * 
     * TC: O(2 x V + E) ~ O(V + E)
     * SC: O(V) + O(V) + O(V) ~ O(V)
     */
    private boolean hasCycleDFS(int n, Map<Integer, ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[n]; // SC: O(V)
        boolean[] inRecursion = new boolean[n]; // SC: O(V)
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (!visited[i] && 
                dfsDirectedGraphCycle(i, adj, visited, inRecursion)) { // TC: O(V + E), SC: O(V)
                return true;
            }
        }
        return false;
    }
    
    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V) - recursion stack space
     */
    private boolean dfsDirectedGraphCycle(int u, Map<Integer, ArrayList<Integer>> adj,
        boolean[] visited, boolean[] inRecursion) {
        visited[u] = true;
        inRecursion[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v] && dfsDirectedGraphCycle(v, adj, visited, inRecursion)) {
                return true;
            } else if (inRecursion[v]) {
                return true;
            }
        }
        inRecursion[u] = false;
        return false;
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V) - recursion stack space
     */
    private void dfsGraph(int u, Map<Integer, ArrayList<Integer>> adj,
        Stack<Integer> st, boolean[] visited) {
        visited[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v]) {
                dfsGraph(v, adj, st, visited);
            }
        }
        st.push(u);
    }
    
    /**
     * Using Hashing Approach
     * 
     * TC: O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
        }
        return adj;
    }
}
