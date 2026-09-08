class Solution {
    private int[][] directions = {
        { -1, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 },
        { 0, -1 }, { -1, 0 }, { 1, 0 }, { 0, 1 }        
    };

    /**
     * Approach : Using Matrix Traversal Approach
     * 
     * TC : O(n x m x l)
     * SC : O(1)
     */
    public ArrayList<ArrayList<Integer>> searchWord(char[][] mat, String word) {
        int n = mat.length;
        int m = mat[0].length;
        int len = word.length();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {     // TC : O(n)
            for (int j = 0; j < m; j++) { // TC : O(m)
                // check if starting character of 'word' matches first
                if (mat[i][j] != word.charAt(0)) {
                    continue;
                }
                // now we can explore all 8 directions
                ArrayList<Integer> coor = new ArrayList<>();
                boolean isMatched = false;
                for (int[] dir : directions) { // TC : O(8)
                    int k = 1; // as 1st character matched already
                    int r = i;
                    int c = j;
                    while (k < len) { // TC : O(l)
                        int effX = r + dir[0];
                        int effY = c + dir[1];
                        if (effX < 0 || effX >= n || effY < 0 || effY >= m) {
                            // invalid cells
                            break;
                        }
                        if (word.charAt(k) != mat[effX][effY]) {
                            break;
                        }
                        r = effX;
                        c = effY;
                        k++;
                    }
                    if (k == len) {
                        isMatched = true;
                    }
                }
                if (isMatched) {
                    coor.add(i);
                    coor.add(j);
                    result.add(coor);
                }
            }
        }
        return result;
    }
};
