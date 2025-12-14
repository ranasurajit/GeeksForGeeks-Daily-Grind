class Solution {
    /**
     * Approach II : Using Optimal (2-D Matrix Simulation + Prefix Sum) Approach
     * 
     * TC: O(N x M + Q)
     * SC: O(N x M)
     * 
     * Accepted (1112 / 1112 testcases passed)
     */
    public ArrayList<Integer> prefixSum2D(int[][] mat, int[][] queries) {
        int n = mat.length;
        int m = mat[0].length;
        long[][] prefix = new long[n][m]; // SC: O(N x M)
        for (int i = 0; i < n; i++) {     // TC: O(N)
            for (int j = 0; j < m; j++) { // TC: O(M)
                prefix[i][j] = (long) mat[i][j] + 
                    (i > 0 ? prefix[i - 1][j] : 0L) + 
                    (j > 0 ? prefix[i][j - 1] : 0L) - 
                    (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0L);
            }
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int[] query : queries) { // TC: O(Q)
            int sr = query[0];
            int sc = query[1];
            int er = query[2];
            int ec = query[3];
            long sum = prefix[er][ec] - 
                (sr > 0 ? prefix[sr - 1][ec] : 0) - 
                (sc > 0 ? prefix[er][sc - 1] : 0) + 
                (sr > 0 && sc > 0 ? prefix[sr - 1][sc - 1] : 0);
            result.add((int) sum);
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force (2-D Matrix Simulation) Approach
     * 
     * TC: O(Q x N x M)
     * SC: O(1)
     * 
     * Time Limit Exceeded (1110 / 1112 testcases passed)
     */
    public ArrayList<Integer> prefixSum2DBruteForce(int[][] mat, int[][] queries) {
        int n = mat.length;
        int m = mat[0].length;
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int[] query : queries) { // TC: O(Q)
            int sr = query[0];
            int sc = query[1];
            int er = query[2];
            int ec = query[3];
            result.add(sumMatrix(mat, sr, sc, er, ec)); // TC: O(N x M)
        }
        return result;
    }

    /**
     * Using 2-D Matrix Simulation Approach
     * 
     * TC: O(Range(Row) x Range(Col))
     * SC: O(1)
     */
    private int sumMatrix(int[][] mat, int sr, int sc, int er, int ec) {
        int sum = 0;
        for (int i = sr; i <= er; i++) { // TC: O(Range(Row))
            for (int j = sc; j <= ec; j++) { // TC: O(Range(Col))
                sum += mat[i][j];
            }
        }
        return sum;
    }
}
