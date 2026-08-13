class Solution {
    /**
     * Approach : Using Topological Sort (Kahn's Algorithm) + DP Approach
     * 
     * TC : O(e) + O(v) + O(v + e) + O(v + e) ~ O(v + e)
     * SC : O(v) + O(v + e) + O(v) + O(v) ~ O(v + e)
     */
    public int[] maxDistance(int V, int src, ArrayList<ArrayList<Integer>> edges) {
        /**
         * we can compute the Topological Sort of the vertices as
         * this graph represents a Directed Acyclic Graph (DAG)
         * so, we can use Kahn's Algorithm to do that
         */
        int[] indegrees = new int[V]; // SC : O(v)
        Map<Integer, ArrayList<int[]>> adj = new HashMap<>(); // SC : O(v + e)
        for (ArrayList<Integer> edge : edges) { // TC : O(e)
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);
            indegrees[v]++;
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[] { v, w });
        }
        Queue<Integer> queue = new LinkedList<>(); // SC : O(v)
        for (int u = 0; u < V; u++) { // TC : O(v)
            if (indegrees[u] == 0) {
                queue.offer(u);
            }
        }
        ArrayList<Integer> topoSort = new ArrayList<>(); // SC : O(v)
        while (!queue.isEmpty()) { // TC : O(v)
            Integer u = queue.poll();
            topoSort.add(u);
            ArrayList<int[]> neighbours = adj.get(u);
            if (neighbours == null) {
                continue;
            }
            for (int[] ngbr : neighbours) { // TC : O(e)
                int v = ngbr[0];
                indegrees[v]--;
                if (indegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        /**
         * now we have the topological sort of the nodes
         * now we can compute the longest path from vertex 
         * node 'src' to all by using DP 'longest' array
         */
        int[] dist = new int[V]; // SC : O(v)
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[src] = 0;
        for (Integer u : topoSort) { // TC : O(v)
            if (dist[u] == Integer.MIN_VALUE) {
                continue;
            }
            ArrayList<int[]> neighbours = adj.get(u);
            if (neighbours == null) {
                continue;
            }
            for (int[] ngbr : neighbours) { // TC : O(e)
                int v = ngbr[0];
                int w = ngbr[1];
                dist[v] = Math.max(dist[v], w + dist[u]);
            }
        }
        return dist;
    }
}
