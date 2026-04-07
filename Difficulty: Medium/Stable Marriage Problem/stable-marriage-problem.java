class Solution {
    /**
     * Approach : Using Queue + Array Simulation Approach
     * 
     * TC: O(n²) + O(n) + O(n) + O(n) ~ O(n²)
     * SC: O(n²) + O(n) + O(n) + O(n) ~ O(n²)
     */
    public int[] stableMarriage(int[][] men, int[][] women) {
        int n = women.length;
        int[][] rank = new int[n][n];     // SC: O(n²)
        for (int w = 0; w < n; w++) {     // TC: O(n)
            for (int j = 0; j < n; j++) { // TC: O(n)
                int m = women[w][j];
                rank[w][m] = j;
            }
        }
        Queue<Integer> freeMen = new LinkedList<>(); // SC: O(n)
        for (int i = 0; i < n; i++) {  // TC: O(n)
            freeMen.offer(i);
        }
        int[] womenAlloc = new int[n]; // SC: O(n)
        Arrays.fill(womenAlloc, -1);
        int[] pIndex = new int[n];     // SC: O(n)
        while (!freeMen.isEmpty()) {   // TC: O(n)
            int m = freeMen.poll();
            int w = men[m][pIndex[m]];
            pIndex[m]++;
            if (womenAlloc[w] == -1) {
                // we can propose to make the pair
                womenAlloc[w] = m;
            } else {
                // women w is already paired with a man
                int menIndex = womenAlloc[w];
                // we need to allocate the men to women whose rank is better
                if (rank[w][menIndex] > rank[w][m]) {
                    // free up the men 'menIndex'
                    freeMen.offer(menIndex);
                    womenAlloc[w] = m;
                } else {
                    // rejected man 'm' goes back
                    freeMen.offer(m);
                }
            }
        }
        int[] result = new int[n];
        for (int w = 0; w < n; w++) { // TC: O(n)
            int m = womenAlloc[w];
            result[m] = w;
        }
        return result;
    }
}
