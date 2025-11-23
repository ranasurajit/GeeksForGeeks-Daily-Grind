class Solution {
    /**
     * Approach : Using Disjoint Set Union Find (DSU) Approach
     * 
     * TC: O(N) + O(N x N x α(N)) + O(N x α(N)) ~ O(N x N x α(N)) ~ O(N²)
     * SC: O(N) + O(N) + O(2 x log(N)) ~ O(N)
     */
    int maxRemove(int[][] stones) {
        int n = stones.length;
        int[] parent = new int[n]; // SC: O(N)
        int[] rank = new int[n];   // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            parent[i] = i;
            rank[i] = 1;
        }
        /**
         * Intuition is the compute all the disconnected components
         * and each stone from each disconnected component should
         * remain and rest we can remove at max
         */
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    // same row or column
                    int iParent = find(parent, i);
                    int jParent = find(parent, j);
                    if (iParent != jParent) {
                        unionByRank(iParent, jParent, parent, rank); // TC: O(1), SC: O(1)
                    }
                }
            }
        }
        // we need to find all the count of disconnected components
        int disconnected = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (find(parent, i) == i) { // TC: O(α(N)), SC: O(log(N))
                disconnected++;
            }
        }
        // minimum retained stones = disconnected (1 from each components)
        return n - disconnected;
    }
    
    /**
     * Using DSU (Find By Path Compression) Approach
     * 
     * TC: O(α(V))
     * SC: O(log(V))
     */
    private int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent, parent[x]);
    }
    
    /**
     * Using DSU (Union By Rank) Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */ 
    private void unionByRank(int xParent, int yParent, int[] parent, int[] rank) {
        if (rank[xParent] > rank[yParent]) {
            // make y's parent as child of x's parent
            parent[yParent] = xParent;
        } else if (rank[yParent] > rank[xParent]) {
            // make x's parent as child of y's parent
            parent[xParent] = yParent;
        } else {
            // make anyone as parent and increase it's rank
            // make y's parent as child of x's parent
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }
};
