class Solution {
    /**
     * Approach I : Using Linear Search Simulation Approach
     * 
     * TC : O(m x n)
     * SC : O(1)
     */
    public ArrayList<Integer> getMarks(int[] l, int[] r, int[] rank) {
        int n = l.length;
        int m = rank.length;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < m; i++) { // TC : O(m)
            int marks = getMarksForRank(l, r, n, rank[i]); // TC : O(n)
            result.add(marks);
        }
        return result;
    }
    
    /**
     * Using Linear Search Simulation Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    private int getMarksForRank(int[] l, int[] r, int n, int rank) {
        int count = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            count += (r[i] - l[i] + 1);
            if (rank <= count) {
                int offset = count - rank;
                // early exit
                return r[i] - offset;
            }
        }
        return -1;
    }
}
