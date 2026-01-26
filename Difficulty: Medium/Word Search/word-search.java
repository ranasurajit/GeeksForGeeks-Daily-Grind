class Solution {
    private static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, {0, 1} };
    private int n;
    private int m;

    /**
     * Approach : Using Backtracking Approach
     * 
     * TC: O(N x M x (3 ^ L))
     * SC: O(N x M x L)
     * 
     * where L = size(word)
     */
    public boolean isWordExist(char[][] mat, String word) {
        this.n = mat.length;
        this.m = mat[0].length;
        for (int i = 0; i < n; i++) {     // TC: O(N)
            for (int j = 0; j < m; j++) { // TC: O(M)
                if (mat[i][j] == word.charAt(0) && 
                    backtrack(0, i, j, mat, word)) { // TC: O(3 ^ L), SC: O(L)
                    return true;
                }                
            }
        }
        return false;
    }
    
    /**
     * Using Backtracking Approach
     * 
     * TC: O(3 ^ L)
     * SC: O(L)
     * 
     * where L = size(word)
     */
    private boolean backtrack(int idx, int i, int j, char[][] mat, String word) {
        // Base Case
        if (idx == word.length()) {
            // we could get all characters of word so idx is exhaused
            return true;
        }
        if (i < 0 || i >= n || j < 0 || j >= m || mat[i][j] != word.charAt(idx)) {
            // handling invalid cases
            return false;
        }
        // mat[i][j] matches with word.charAt(idx)
        // Recursion Calls
        char temp = mat[i][j];
        // marking cell as visited as same cell cannot be used more than once
        mat[i][j] = '$'; // modify
        for (int[] dir : directions) {
            int effX = i + dir[0];
            int effY = j + dir[1];
            if (backtrack(idx + 1, effX, effY, mat, word)) {
                return true;
            }
        }
        mat[i][j] = temp; // backtrack
        return false;
    }
}
