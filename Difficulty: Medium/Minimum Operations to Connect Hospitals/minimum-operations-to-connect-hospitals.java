class Solution {
    /**
     * Approach : Using Disjoint Set Union Find (DSU) Approach
     * 
     * TC: O(V) + O(2 x E x α(V)) + O(V) ~ O(E x α(V))
     * SC: O(V) + O(V) + O(2 x log(V)) ~ O(V)
     * 
     * where α(V) is the Inverse Ackermann Function ~ almost constant
     */
    public int minConnect(int V, int[][] edges) {
        int[] parent = new int[V];    // SC: O(V)
        int[] rank = new int[V];      // SC: O(V)
        for (int i = 0; i < V; i++) { // TC: O(V)
            parent[i] = i;
            rank[i] = 1;
        }
        int minEdgesNeeded = 0;
        for (int[] edge : edges) { // TC: O(E)
            int parentU = find(parent,  edge[0]); // TC: O(α(V)), SC: O(log(V))
            int parentV = find(parent, edge[1]);  // TC: O(α(V)), SC: O(log(V))
            if (parentU == parentV) {
                continue;
            }
            unionByRank(parentU, parentV, parent, rank); // TC: O(1), SC: O(1)
            minEdgesNeeded++;
        }
        // now we will count number of disconnected components
        int components = 0;
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (parent[i] == i) {
                components++;
            }    
        }
        // to connect C disconnected components we need more edges (C - 1)
        int edgesNeeded = components - 1;
        // now we need to check if we have those many extra edges
        int extraEdges = edges.length - minEdgesNeeded;
        if (extraEdges < edgesNeeded) {
            // if we have less extra edges than needed, then we cannot connect all hospitals
            return -1;
        }
        return edgesNeeded;
    }

    /**
     * Using DSU - Find By Path Compression Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private void unionByRank(int xParent, int yParent, int[] parent, int[] rank) {
        if (rank[xParent] > rank[yParent]) {
            // make y's parent as x's child
            parent[yParent] = xParent;
        } else if (rank[yParent] > rank[xParent]) {
            // make x's parent as y's child
            parent[xParent] = yParent;
        } else {
            // make y's parent as x's child increasing rank of xParent
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }

    /**
     * Using DSU - Find By Path Compression Approach
     * 
     * TC: O(α(V))
     * SC: O(log(V))
     */
    private int find(int[] parent, int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent, parent[x]);
    }
}
