class Solution {
    /**
     * Approach : Using Hashing + Array Simulation Approach
     * 
     * TC: O(N)
     * SC: O(2) ~ O(1)
     */
    public boolean canServe(int[] arr) {
        int n = arr.length;
        // In the below array we can store denominations of 5 and 10 respectively
        int[] denominations = { 0, 0 }; // SC: O(2)
        int i = 0;
        for (i = 0; i < n; i++) { // TC: O(N)
            if (arr[i] == 5) {
                denominations[0]++;
            } else if (arr[i] == 10) {
                if (denominations[0] > 0) {
                    // returning 1 denomination of 5
                    denominations[1]++;
                    denominations[0]--;
                } else {
                    break;
                }
            } else {
                if (denominations[1] > 0 && denominations[0] > 0) {
                    // returning 1 denomination of 5 and 1 denomination of 10
                    denominations[0]--;
                    denominations[1]--;
                } else if (denominations[0] > 2) {
                    // returning 3 denominations of 5
                    denominations[0] -= 3;
                } else {
                    break;
                }
            }
        }
        return i == n;
    }
}
