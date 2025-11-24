class Solution {
    /**
     * Approach : Using Kruskal's Algorithm (Disjoint Set Union Find) Approach
     * 
     * TC: O(V) + O(E x log(E)) + O(E x α(V)) + O(K x V) + O(V x E x α(V)) ~ O(E x (log(E) + V))
     * SC: O(V) + O(V) + O(E x log(V)) + O(K x V) + O(K x E x log(V)) ~ O(E x log(V))
     */
    public int secondMST(int V, int[][] edges) {
        int e = edges.length;
        /**
         * We will be using Kruskal's Algorithm to find MST weight
         * so that later on we can compare this to get the second
         * Best MST (Minimum Spanning Tree)
         */
        DSU dsuFirst = new DSU(V); // TC: O(V), SC: O(V)
        Arrays.sort(edges, (a, b) -> a[2] - b[2]); // TC: O(E x log(E))
        int mstWeight = 0;
        List<Integer> usedEdgesIndices = new ArrayList<Integer>(); // SC: O(V)
        for (int i = 0; i < e; i++) { // TC: O(E)
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            int uParent = dsuFirst.find(u); // TC: O(α(V)), SC: O(log(V))
            int vParent = dsuFirst.find(v); // TC: O(α(V)), SC: O(log(V))
            if (uParent == vParent) {
                continue;
            }
            dsuFirst.unionByRank(uParent, vParent); // TC: O(1), SC: O(1)
            mstWeight += w;
            usedEdgesIndices.add(i);
        }
        // MST cannot be formed if selected Edges != (V - 1)
        if (usedEdgesIndices.size() != V - 1) {
            return -1;
        }
        // Now we need to again run DSU by removing each usedEdges and compare with mstWeight
        int secondBestMST = Integer.MAX_VALUE;
        for (Integer edgeIndex : usedEdgesIndices) { // TC: O(V)
            DSU dsuSecond = new DSU(V); // TC: O(V), SC: O(V)
            int currentWeight = 0;
            int edgeCounts = 0;
            for (int i = 0; i < e; i++) { // TC: O(E)
                if (i == edgeIndex) {
                    continue;
                }
                int u = edges[i][0];
                int v = edges[i][1];
                int w = edges[i][2];
                int uParent = dsuSecond.find(u); // TC: O(α(V)), SC: O(log(V))
                int vParent = dsuSecond.find(v); // TC: O(α(V)), SC: O(log(V))
                if (uParent == vParent) {
                    continue;
                }
                dsuSecond.unionByRank(uParent, vParent); // TC: O(1), SC: O(1)
                currentWeight += w;
                edgeCounts++;
            }
            // MST can be formed if selected Edges = (V - 1)
            if (edgeCounts == V - 1 && mstWeight < currentWeight) {
                secondBestMST = Math.min(secondBestMST, currentWeight);
            }
        }
        return secondBestMST == Integer.MAX_VALUE ? -1 : secondBestMST;
    }
    
    /**
     * Using Disjoint Set Union Approach (Utility)
     */
    private class DSU {
        private int[] parent;
        private int[] rank;
        
        /**
         * Using DSU Approach
         * 
         * TC: O(N)
         * SC: O(N)
         */
        public DSU(int V) {
            this.parent = new int[V];  // SC: O(V)
            this.rank = new int[V];    // SC: O(V)
            for (int i = 0; i < V; i++) { // TC: O(V)
                parent[i] = i;
                rank[i] = 1;
            }
        }
        
        /**
         * Using DSU (Find By Path Compression) Approach
         * 
         * TC: O(α(V))
         * SC: O(log(V))
         */
        private int find(int x) {
            if (x == parent[x]) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        
        /**
         * Using DSU (Union By Rank) Approach
         * 
         * TC: O(1)
         * SC: O(1)
         */
        private void unionByRank(int xParent, int yParent) {
            if (rank[xParent] > rank[yParent]) {
                // make yParent's parent to xParent
                parent[yParent] = xParent;
            } else if (rank[yParent] > rank[xParent]) {
                // make xParent's parent to yParent
                parent[xParent] = yParent;
            } else {
                // make yParent's parent to xParent thereby increasing rank(xParent)
                parent[yParent] = xParent;
                rank[xParent]++;
            }
        }
    }
}
