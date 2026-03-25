class Solution {
    /**
     * Approach II : Using Optimal (Graph BFS - Topological Trim Leaf Nodes) Approach
     * 
     * TC: O(E) + O(V) + O(V + E) ~ O(V + E)
     * SC: O(V) + O(V) + O(V + E) ~ O(V + E)
     * 
     * Accepted (1112 / 1112 testcases passed)
     */
    public ArrayList<Integer> minHeightRoot(int V, int[][] edges) {
        int[] indegrees = new int[V]; // SC: O(V)
        Map<Integer, ArrayList<Integer>> adj =
            createGraphAndIndegrees(edges, indegrees); // TC: O(E), SC: O(V + E)
        Queue<Integer> queue = new LinkedList<>(); // SC: O(V)
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (indegrees[i] == 1) {
                // 'i'th node is a leaf node with indegree 1
                queue.offer(i);
            }
        }
        int remainingNodes = V;
        while (remainingNodes > 2) { // TC: O(V)
            int size = queue.size();
            remainingNodes -= size;
            for (int i = 0; i < size; i++) {
                Integer u = queue.poll();
                for (Integer v : adj.getOrDefault(u, new ArrayList<>())) { // TC: O(E)
                    indegrees[v]--;
                    if (indegrees[v] == 1) {
                        queue.offer(v);
                    }
                }
            }
        }
        return new ArrayList<>(queue);
    }
    
    /**
     * Using Hashing Approach
     * 
     * TC: O(2 x E) ~ O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createGraphAndIndegrees(int[][] edges,
        int[] indegrees) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) { // TC: O(2 x E)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
            indegrees[edge[1]]++;
            indegrees[edge[0]]++;
        }
        return adj;
    }

    /**
     * Approach I : Using Brute-Force (Graph BFS - Level Order Traversal) Approach
     * 
     * TC: O(E) + O(V x (V + E))
     * SC: O(V + E) + O(V x E)
     * 
     * Time Limit Exceeded (1110 / 1112 testcases passed)
     */
    public ArrayList<Integer> minHeightRootBruteForce(int V, int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = createGraph(edges); // TC: O(E), SC: O(V + E)
        Map<Integer, ArrayList<Integer>> heightMap = new HashMap<>(); // SC: O(V)
        int minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++) { // TC: O(V)
            /**
             * considering node 'i' as the root of the tree
             * as per Notes: The height of a rooted tree is
             * defined as the maximum number of edges on the
             * path from the root to any leaf node, so we need
             * to perform BFS (Level Order Traversal) to compute
             * the root with minimum number of levels
             */
             boolean[] visited = new boolean[V]; // SC: O(V)
             int currentLevels = bfsGraph(i, visited, adj); // TC: O(V + E), SC: O(V)
             heightMap.computeIfAbsent(currentLevels, k -> new ArrayList<>()).add(i);
             if (currentLevels < minHeight) {
                 minHeight = currentLevels;
             }
        }
        return heightMap.get(minHeight);
    }
    
    /**
     * Using Graph BFS (Level Order Traversal) Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private int bfsGraph(int src, boolean[] visited,
        Map<Integer, ArrayList<Integer>> adj) {
        Queue<Integer> queue = new LinkedList<>(); // SC: O(V)
        queue.offer(src);
        visited[src] = true;
        int levels = 0;
        while (!queue.isEmpty()) { // TC: O(V)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer u = queue.poll();
                for (Integer v : adj.getOrDefault(u, new ArrayList<>())) { // TC: O(E)
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.offer(v);
                    }
                }
            }
            levels++;
        }
        return levels;
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(2 x E) ~ O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) { // TC: O(2 x E)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        return adj;
    }
}
