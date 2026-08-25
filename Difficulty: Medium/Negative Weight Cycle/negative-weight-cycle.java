class Solution {
    /**
     * Approach : Using Bellmann Ford Algorithm Approach
     * 
     * TC : O(e x v) + O(e) ~ O(e x v)
     * SC : O(v)
     */
    public boolean isNegativeWeightCycle(int V, int[][] edges) {
        int INF = (int) 1e8;
        int[] dist = new int[V];       // SC : O(v)
        Arrays.fill(dist, INF);
        dist[0] = 0;
        // we will relax the edges (V - 1) times
        for (int i = 1; i < V; i++) {  // TC : O(v)
            for (int[] edge : edges) { // TC : O(e)
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }
        boolean hasNegWeight = false;
        /**
         * run the loop 1 more time
         */
        for (int[] edge : edges) {    // TC : O(e)
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (dist[u] != INF && dist[u] + w < dist[v]) {
                return true;
            }
        }
        return false;
    }
}
