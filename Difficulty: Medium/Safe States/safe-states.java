class Solution {
    /**
     * Approach : Using Kahn's Algorithm + Topological Sort + Reversing Edges Approach
     * 
     * TC: O(E) + O(V) + O(V + E) ~ O(V + E)
     * SC: O(V) + O(V + E) + O(V) ~ O(V + E)
     */
    public ArrayList<Integer> safeNodes(int V, int[][] edges) {
        int[] indegrees = new int[V]; // SC: O(V)
        /**
         * Generating Reverse Adjancy List would help to identify terminal nodes
         * where nodes with indegree = 0 (outdegree becomes indegree) will be 
         * the entry point using Topological Sort (as nodes within a cycle cannot
         * certainly be a safe node), so using Kahn's Algorithm
         */
        Map<Integer, ArrayList<Integer>> adj =
            createReverseGraph(edges, indegrees); // TC: O(E), SC: O(V + E)
        Queue<Integer> queue = new LinkedList<Integer>(); // SC: O(V)
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        ArrayList<Integer> safeVertices = new ArrayList<Integer>();
        while (!queue.isEmpty()) { // TC: O(V + E)
            int u = queue.poll();
            safeVertices.add(u);
            for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
                indegrees[v]--;
                if (indegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return safeVertices;
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createReverseGraph(int[][] edges, 
        int[] indegrees) {
        Map<Integer, ArrayList<Integer>> adj = 
            new HashMap<Integer, ArrayList<Integer>>(); // SC: O(V + E)
        for (int[] edge : edges) { // TC: O(E)
            adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
            indegrees[edge[0]]++;
        }
        return adj;
    }
}
