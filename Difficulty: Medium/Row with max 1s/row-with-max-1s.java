// User function Template for Java

class Solution {
    /**
     * Approach I : Using Prefix-Sum Approach
     * 
     * TC: O(M x N)
     * SC: O(1)
     */
    public int rowWithMax1s(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        // row with maxSum will have maximum number of 1s
        int maxSum = 0;
        int result = -1;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            int sum = 0;
            for (int j = 0; j < n; j++) { // TC: O(N)
                sum += arr[i][j];
            }
            if (sum > maxSum) {
                maxSum = sum;
                result = i;
            }
        }
        return result;
    }
}
