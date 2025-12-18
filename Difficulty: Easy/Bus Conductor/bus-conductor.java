class Solution {
    /**
     * Approach : Using Sorting Approach
     * 
     * TC: O(N x log(N)) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int findMoves(int[] chairs, int[] passengers) {
        int n = chairs.length;
        /**
         * Since any passenger can occupy any seat so greedily we
         * should try to place the passenger to its nearby chair
         * so we can sort the chairs and passengers position
         */
        Arrays.sort(chairs);     // TC: O(N x log(N))
        Arrays.sort(passengers); // TC: O(N x log(N))
        int moves = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            moves += Math.abs(chairs[i] - passengers[i]);
        }
        return moves;
    }
}
