class Solution {
    /**
     * Approach : Using Array Simulation Approach
     * 
     * TC: O(n)
     * SC: O(1)
     */
    public int countIncreasing(int[] arr) {
        int n = arr.length;
        int runLen = 1;
        int count = 0;
        for (int i = 1; i < n; i++) { // TC: O(n)
            if (arr[i] > arr[i - 1]) {
                runLen++;
            } else {
                count += (((runLen) * (runLen - 1)) / 2);
                runLen = 1;
            }
        }
        count += (((runLen) * (runLen - 1)) / 2);
        return count;
    }
}
