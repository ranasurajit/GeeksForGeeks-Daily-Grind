class Solution {
    /**
     * directions are kept such that 0th index
     * denotes movement towards left to right and
     * all next indices are right (clockwise) turns
     * from current direction
     */
    private static final int[][] directions = {
        { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }
    };
    
    private int n;
    private int m;

    /**
     * Approach : Using DFS Approach
     * 
     * TC : O(n x m)
     * SC : O(n + m)
     */
    public List<Integer> exitPoint(int[][] mat) {
        this.n = mat.length;
        this.m = mat[0].length;
        List<int[]> result = new ArrayList<>();
        solveDFS(0, 0, mat, 0, result);
        List<Integer> answer = new ArrayList<>();
        int[] first = result.get(0);
        answer.add(first[0]);
        answer.add(first[1]);
        return answer;
    }
    
    /**
     * Using DFS Approach
     * 
     * TC : O(n x m)
     * SC : O(n + m)
     */
    private void solveDFS(int i, int j, int[][] mat,
        int dirIdx, List<int[]> result) {
        // Base Case
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return;
        }
        // Recursion Calls
        int selectedIdx = mat[i][j] == 0 ? dirIdx : ((dirIdx + 1) % 4);
        if (mat[i][j] == 1) {
            mat[i][j] = 0;   
        }
        int[] dir = directions[selectedIdx];
        int effI = i + dir[0];
        int effJ = j + dir[1];
        if (effI < 0 || effI >= n || effJ < 0 || effJ >= m) {
            result.add(new int[] { i, j });
        }
        solveDFS(effI, effJ, mat, selectedIdx, result);
    }
}
