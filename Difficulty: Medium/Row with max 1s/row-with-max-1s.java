// User function Template for Java

class Solution {
    /**
     * Approach II : Using Top-Right Greedy Scan Approach
     * 
     * TC: O(M + N)
     * SC: O(1)
     */
    public int rowWithMax1s(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        int result = -1;
        int j = n - 1;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            // scan from right to left
            while (j >= 0 && arr[i][j] == 1) { // TC: O(N) -> ammortized
                // it will go more left in case of row having max1s
                j--;
                result = i;
            }
        }
        return result;
    }

    /**
     * Approach II : Using Binary Search Approach
     * 
     * TC: O(M x log(N))
     * SC: O(1)
     */
    public int rowWithMax1sBinarySearch(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        int result = -1;
        int max1s = 0;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            int countCurrent1s = n - lowerBound(arr[i], n, 1); // TC: O(log(N))
            if (countCurrent1s > max1s) {
                max1s = countCurrent1s;
                result = i;
            }
        }
        return result;
    }
    
    /**
     * Using Binary Search (To Find Lower Bound) Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int lowerBound(int[] row, int n, int x) {
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (row[mid] >= x) {
                // moving left to get lower bound
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Approach I : Using Prefix-Sum Approach
     * 
     * TC: O(M x N)
     * SC: O(1)
     */
    public int rowWithMax1sUsingPrefixSum(int arr[][]) {
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
