class Solution {
    /**
     * Approach : Using Binary Search on Answers Approach
     * 
     * TC: O(N) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(1)
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int low = 0;
        int high = Integer.MIN_VALUE;
        for (int x : citations) { // TC: O(N)
            high = Math.max(high, x);
        }
        int index = 0;
        while (low <= high) {     // TC: O(log(N))
            int mid = low + (high - low) / 2;
            int citationsMoreThanEqualsMid =
                computeCitationsMoreThanEqualsMid(citations, mid); // TC: O(N)
            if (citationsMoreThanEqualsMid >= mid) {
                index = mid;
                // increase the mid
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return index;
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private int computeCitationsMoreThanEqualsMid(int[] citations, int mid) {
        int count = 0;
        for (int x : citations) { // TC: O(N)
            if (x >= mid) {
                count++;
            }
        }
        return count;
    }
}
