class Solution {
    /**
     * Approach II : Using Hashing Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        // H-Index can never be more than 'n'
        int[] bucket = new int[n + 1]; // SC: O(N)
        for (int c : citations) { // TC: O(N)
            if (c >= n) {
                bucket[n]++;
            } else {
                bucket[c]++;
            }
        }
        int cumulativeSum = 0;
        for (int i = n; i >= 0; i--) { // TC: O(N)
            cumulativeSum += bucket[i];
            if (cumulativeSum >= i) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Approach I : Using Binary Search on Answers Approach
     * 
     * TC: O(N) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(1)
     */
    public int hIndexBinarySearch(int[] citations) {
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
