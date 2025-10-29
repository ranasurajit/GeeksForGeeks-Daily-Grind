class Solution {
    /**
     * Approach : Using BFS Approach
     * 
     * TC: O(E) + O(V + E) + O(V + E)
     * SC: O(V) + O(V) + O(V + E) ~ O(V + E)
     */
    public int diameter(int V, int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = createGraph(edges); // TC: O(E), SC: O(V + E)
        int lastNode = bfsGraph(adj, V); // TC: O(V + E), SC: O(V)
        int diameter = bfsGraphDistance(adj, V, lastNode); // TC: O(V + E), SC: O(V)
        return diameter;
    }

    /**
     * Using BFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V) + O(V) ~ O(V)
     */
    private int bfsGraphDistance(Map<Integer, ArrayList<Integer>> adj, int V, int start) {
        boolean[] visited = new boolean[V];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] { start, 0 });
        visited[start] = true;
        int maxDist = 0;
        int maxDistNode = -1;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int u = current[0];
            int w = current[1];
            if (maxDist < w) {
                maxDist = w;
                maxDistNode = u;
            }
            for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(new int[] { v, w + 1 });
                }
            }
        }
        return maxDist;
    }
    
    /**
     * Using BFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V) + O(V) ~ O(V)
     */
    private int bfsGraph(Map<Integer, ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        Queue<int[]> queue = new LinkedList<int[]>(); // SC: O(V)
        queue.offer(new int[] { 0, 0 });
        visited[0] = true;
        int maxDist = 0;
        int maxDistNode = -1;
        while (!queue.isEmpty()) { // TC: O(V + E)
            int[] current = queue.poll();
            int u = current[0];
            int w = current[1];
            if (maxDist <= w) {
                maxDist = w;
                maxDistNode = u;
            }
            for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(new int[] { v, w + 1 });
                }
            }
        }
        return maxDistNode;
    }
    
    /**
     * Using Hashing Approach
     * 
     * TC: O(2 x E) ~ O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = 
            new HashMap<Integer, ArrayList<Integer>>(); // SC: O(V + E)
        for (int[] edge : edges) { // TC: O(E)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
        }
        return adj;
    }
}
