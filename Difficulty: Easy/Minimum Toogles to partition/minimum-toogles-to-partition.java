class Solution {
    /**
     * Approach : Using Array Simulation Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    int minToggle(int[] arr) {
        int n = arr.length;
        int totalZeroes = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            totalZeroes += arr[i] == 0 ? 1 : 0;
        }
        int countOnesTillNow = 0;
        int countZeroesLeft = totalZeroes;
        int minOperations = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC : O(n)
            minOperations = 
                Math.min(minOperations, 
                    countZeroesLeft + countOnesTillNow);
            if (arr[i] == 0) {
                countZeroesLeft--;
            } else {
                countOnesTillNow++;
            }
        }
        minOperations = 
                Math.min(minOperations, countOnesTillNow);
        return minOperations;
    }
}
