class Solution {
    private int n;
    private int m;
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    
    /**
     * Approach : Using Dijkstra's Algorithm Approach
     * 
     * TC: O(N x M x log(N x M))
     * SC: O(N x M) + O(N x M) ~ O(N x M)
     */
    public int minCostPath(int[][] mat) {
        this.n = mat.length;
        this.m = mat[0].length;
        int[][] minCost = new int[n][m]; // SC: O(N x M)
        for (int[] mc : minCost) {
            Arrays.fill(mc, (int) 1e9 + 1);
        }
        minCost[0][0] = 0;
        // we will store { effort, rowIdx, colIdx } in Min-Heap 
        PriorityQueue<int[]> pq = 
            new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(N x M)
        pq.offer(new int[] { 0, 0, 0 });
        while (!pq.isEmpty()) { // TC: O(N x M)
            int[] current = pq.poll();
            int effort = current[0];
            int row = current[1];
            int col = current[2];
            if (row == n - 1 && col == m - 1) {
                return effort;
            }
            for (int[] dir : directions) { // TC: O(4)
                int effRow = row + dir[0];
                int effCol = col + dir[1];
                if (effRow >= 0 && effRow < n && effCol >= 0 && effCol < m) {
                    int newEffort = Math.max(effort, 
                        Math.abs(mat[effRow][effCol] - mat[row][col]));
                    if (newEffort < minCost[effRow][effCol]) {
                        minCost[effRow][effCol] = newEffort;
                        pq.offer(new int[] { newEffort, effRow, effCol });
                    }
                }
            }
        }
        return 0;
    }
}
