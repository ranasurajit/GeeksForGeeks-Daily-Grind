class Solution {
    /**
     * Approach : Using BFS Approach
     * 
     * TC: O(E) + O(E) + O(E x (V + E))
     * SC: O(V + E) + O(V) + O(V) ~ O(V + E)
     */
    public int shortCycle(int V, int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = createGraph(edges); // TC: O(E), SC: O(V + E)
        int[] indegrees = getIndegrees(V, edges); // TC: O(E), SC: O(V)
        int minCycle = Integer.MAX_VALUE;
        for (int[] edge : edges) { // TC: O(E)
            int u = edge[0];
            int v = edge[1];
            if (indegrees[u] <= 1) {
                continue;
            }
            minCycle = Math.min(minCycle, bfsGraph(V, u, v, adj)); // TC: O(V + E), SC: O(V)
        }
        return minCycle == Integer.MAX_VALUE ? -1 : minCycle;
    }
    
    /**
     * Using BFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V) + O(V) + O(V) ~ O(V)
     */
    private int bfsGraph(int V, int start, int parent, Map<Integer, ArrayList<Integer>> adj) {
        Queue<Pair> queue = new LinkedList<Pair>();  // SC: O(V)
        boolean[] visited = new boolean[V]; // SC: O(V)
        int[] dist = new int[V]; // SC: O(V)
        Arrays.fill(dist, -1);
        queue.offer(new Pair(start, parent, 0));
        visited[start] = true;
        dist[start] = 0;
        int minCycleLength = Integer.MAX_VALUE;
        while (!queue.isEmpty()) { // TC: O(V + E)
            Pair current = queue.poll();
            int u = current.node;
            int parentNode = current.parent;
            int d = current.dist;
            for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
                if (!visited[v]) {
                    visited[v] = true;
                    dist[v] = d + 1;
                    queue.offer(new Pair(v, u, d + 1));
                } else if (v != parentNode) {
                    // node v is visited already and here the cycle is found
                    minCycleLength = Math.min(minCycleLength, d + 1 + dist[v]);
                }
            }
        }
        return minCycleLength;
    }
    
    /**
     * Using Simulation Approach
     * 
     * TC: O(E)
     * SC: O(V)
     */
    private int[] getIndegrees(int V, int[][] edges) {
        int[] indegrees = new int[V]; // SC: O(V)
        for (int[] edge : edges) { // TC: O(E)
            indegrees[edge[0]]++;
            indegrees[edge[1]]++;
        }
        return indegrees;
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
        for (int[] edge : edges) { // TC: O(2 x E)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
        }
        return adj;
    }
    
    class Pair {
        int node;
        int parent;
        int dist;
        
        public Pair (int node, int parent, int dist) {
            this.node = node;
            this.parent = parent;
            this.dist = dist;
        }
    }
}
