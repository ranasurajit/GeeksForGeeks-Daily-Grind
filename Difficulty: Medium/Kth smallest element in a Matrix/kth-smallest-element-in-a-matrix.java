class Solution {
    /**
     * Approach III : Using Binary Search on Answers + Staircase Counting Approach
     * 
     * TC: O(N x log(Max(mat) - Min(mat)))
     * SC: O(1)
     */
    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length; // this is a square matrix
        /**
         * As each row and column is sorted, we can make use of 
         * this property of matrix using Binary Search
         */
        int low = mat[0][0];
        int high = mat[n - 1][n - 1];
        while (low < high) { // TC: O(log(Max(mat) - Min(mat)))
            int mid = low + (high - low) / 2;
            int countElementsLessThan = 
                getCountUsingStaircaseCounting(mat, n, mid); // TC: O(N)
            if (countElementsLessThan >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        // we will have answer when low = high
        return low;
    }
    
    /**
     * Using Staircase Counting Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private int getCountUsingStaircaseCounting(int[][] mat, int n, int target) {
        int row = 0;
        int col = n - 1;
        int count = 0;
        while (row < n && col >= 0) { // TC: O(N)
            if (mat[row][col] <= target) {
                count += (col + 1);
                row++;
            } else {
                col--;
            }
        }
        return count;
    }

    /**
     * Approach II : Using Binary Search on Answers Approach
     * 
     * TC: O(log(Max(mat) - Min(mat)) x N x log(N))
     * SC: O(1)
     */
    public int kthSmallestBinarySearch(int[][] mat, int k) {
        int n = mat.length; // this is a square matrix
        /**
         * As each row and column is sorted, we can make use of 
         * this property of matrix using Binary Search
         */
        int low = mat[0][0];
        int high = mat[n - 1][n - 1];
        while (low < high) { // TC: O(log(Max(mat) - Min(mat)))
            int mid = low + (high - low) / 2;
            int countElementsLessThan = 
                getCountOfElements(mat, n, mid); // TC: O(N x log(N))
            if (countElementsLessThan >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        // we will have answer when low = high
        return low;
    }
    
    /**
     * Using Binary Search (To Count Elements < target accross all rows) Approach
     * 
     * TC: O(N x log(N))
     * SC: O(1)
     */
    private int getCountOfElements(int[][] mat, int n, int target) {
        int count = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            count += upperBound(mat[i], n, target); // TC: O(log(N))
        }
        return count;
    }
    
    /**
     * Using Binary Search (To Find Upper Bound) Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int upperBound(int[] row, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (row[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Approach I : Using Max-Heap Approach
     * 
     * TC: O(N² x log(K))
     * SC: O(K)
     */
    public int kthSmallestBruteForce(int[][] mat, int k) {
        int n = mat.length; // this is a square matrix
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((p, q) -> q - p); // SC: O(K)
        for (int i = 0; i < n; i++) {     // TC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (pq.size() < k) {
                    pq.offer(mat[i][j]);  // TC: O(log(K))
                } else {
                    if (pq.peek() > mat[i][j]) {
                        pq.poll();
                        pq.offer(mat[i][j]); // TC: O(log(K))
                    }
                }
            }
        }
        return pq.peek();
    }
}
