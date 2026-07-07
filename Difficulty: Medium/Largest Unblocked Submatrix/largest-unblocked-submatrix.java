class Solution {
    private static final int[][] directions = {
        { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 },
        { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 }
    };
    
    private int n;
    private int m;
    
    /**
     * Approach II : Using Greedy + Sorting Approach
     * 
     * TC : O(k) + O(k x log(k)) ~ O(k x log(k))
     * SC : O(k)
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int largestArea(int n, int m, int[][] arr) {
        int k = arr.length; 
        if (k == 0) {
            return n * m;
        }
        int[] blockRows = new int[k]; // SC : O(k)
        int[] blockCols = new int[k]; // SC : O(k)
        for (int i = 0; i < k; i++) { // TC : O(k)
            blockRows[i] = arr[i][0] - 1;
            blockCols[i] = arr[i][1] - 1;
        }
        /**
         * Greedily, we will sort the blocked rows and columns
         * so that we can compute the continuous space 
         */
        Arrays.sort(blockRows); // TC : O(k x log(k))
        Arrays.sort(blockCols); // TC : O(k x log(k))

        int maxVerticalGap = 0;
        int prev = -1;
        for (int v : blockRows) { // TC : O(k)
            maxVerticalGap = Math.max(maxVerticalGap, v - prev - 1);
            prev = v;
        }
        maxVerticalGap = Math.max(maxVerticalGap, n - prev - 1);
        
        int maxHorizontalGap = 0;
        prev = -1;
        for (int c : blockCols) { // TC : O(k)
            maxHorizontalGap = Math.max(maxHorizontalGap, c - prev - 1);
            prev = c;
        }
        maxHorizontalGap = Math.max(maxHorizontalGap, m - prev - 1);
        return maxVerticalGap * maxHorizontalGap;
    }

    /**
     * Approach I : Using Graph + DFS Approach
     * 
     * TC : O(m x n x Min(m, n))
     * SC : O(m x n)
     * 
     * Time Limit Exceeded (1110 / 1111 testcases passed)
     */
    public int largestAreaDFS(int n, int m, int[][] arr) {
        this.n = n;
        this.m = m;
        int k = arr.length; 
        if (k == 0) {
            return n * m;
        }
        Set<Integer> blockRowSet = new HashSet<>(); // SC : O(n)
        Set<Integer> blockColSet = new HashSet<>(); // SC : O(m)
        for (int[] x : arr) { // TC : O(k)
            blockRowSet.add(x[0] - 1); // making 0-based index
            blockColSet.add(x[1] - 1); // making 0-based index
        }
        /**
         * Now we can try doing DFS in all 4 directions
         * to compute the 'largest continuous unblocked area'
         * starting from any unblocked cell
         */
        int maxCount = 0;
        int[][] grid = new int[n][m];     // SC : O(m x n)
        for (int i = 0; i < n; i++) {     // TC : O(n)
            for (int j = 0; j < m; j++) { // TC : O(m)
                if (blockRowSet.contains(i) || blockColSet.contains(j)) {
                    grid[i][j] = -1; // unreachable(blocked) cells
                }
            }
        }
        for (int i = 0; i < n; i++) {     // TC : O(n)
            for (int j = 0; j < m; j++) { // TC : O(m)
                if (grid[i][j] != -1) {
                    int[] currentCount = { 0 };
                    dfsGrid(i, j, grid, currentCount); // TC : O(Min(m, n))
                    maxCount = Math.max(maxCount, currentCount[0]);
                }
            }
        }
        return maxCount;
    }
    
    /**
     * Using DFS Approach
     * 
     * TC : O(8 x Min(m, n)) ~ O(Min(m, n))
     * SC : O(Min(m, n))
     */
    private void dfsGrid(int i, int j, int[][] grid, int[] count) {
        grid[i][j] = -1; // visited
        count[0]++; // increment count for every new visits
        // explore in all 8 directions
        for (int[] dir : directions) { // TC : O(8)
            int i_ = i + dir[0];
            int j_ = j + dir[1];
            if (i_ < 0 || i_ >= n || j_ < 0 || j_ >= m || grid[i_][j_] == -1) {
                // invalid boundary or already visited
                continue;
            }
            dfsGrid(i_, j_, grid, count);
        }
    }
}
