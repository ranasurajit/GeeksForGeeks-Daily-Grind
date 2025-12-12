class Solution {
    /**
     * Approach : Using 2-D Array Simulation Approach
     * 
     * TC: O(N x N) + O(N x N) ~ O(N x N)
     * SC: O(1)
     */
    public ArrayList<ArrayList<Integer>> transpose(int[][] mat) {
        int n = mat.length;
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < i; j++) { // TC: O(N)
                // in-place swap of values
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
        ArrayList<ArrayList<Integer>> trans = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) { // TC: O(N)
                row.add(mat[i][j]);
            }
            trans.add(row);
        }
        return trans;
    }
}
