class Solution {
    /**
     * Approach : Using Disjoint Set Union Approach
     * 
     * TC : O(n) + O(m x α(n)) + O(n x α(n)) ~ O(n + (m + n) x α(n)) ~ O(n + m)
     * SC : O(n) + O(n) + O(α(n)) ~ O(n)
     */
    int minEdgesReq(int n, int[][] edges) {
        int[] parents = new int[n];   // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            parents[i] = i;
        }
        int extraEdges = 0;
        int[] rank = new int[n];      // SC : O(n)
        for (int[] edge : edges) {    // TC : O(m)
            int u = edge[0];
            int v = edge[1];
            int uParent = find(u, parents); // TC : O(α(n)), SC : O(α(n))
            int vParent = find(v, parents); // TC : O(α(n)), SC : O(α(n))
            if (uParent == vParent) {
                extraEdges++;
                continue;
            }
            // create the edge between uParent and vParent
            unionByRank(uParent, vParent, parents, rank); // TC : O(1), SC : O(1)
        }
        /**
         * now we will compute the number of disconnected
         * components that needs to be connected
         * 
         * so, if we have 'p' components that is disconnected
         * then we need minimum (p - 1) operations to make all
         * components connected
         */
        int components = 0;
        for (int i = 0; i < n; i++) {       // TC : O(n)
            if (find(i, parents) == i) {    // TC : O(α(n))
                components++;
            }
        }
        int edgesNeeded = components - 1;
        if (edgesNeeded > extraEdges) {
            return -1;
        }
        return edgesNeeded;
    }
    
    /**
     * Using DSU (Find by Path Compression) Approach
     * 
     * TC : O(α(n))
     * SC : O(α(n))
     */
    private int find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x], parent);
    }
    
    /**
     * Using DSU (Union By Rank) Approach
     * 
     * TC : O(1)
     * SC : O(1)
     */
    private void unionByRank(int xParent, int yParent,
        int[] parent, int[] rank) {
        if (xParent == yParent) {
            return;
        }
        if (rank[xParent] > rank[yParent]) {
            // make xParent as parent of yParent
            parent[yParent] = xParent;
        } else if (rank[xParent] < rank[yParent]) {
            // make yParent as parent of xParent
            parent[xParent] = yParent;
        } else {
            // make xParent as parent of yParent and increase its rank
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }
}
