class Solution {
    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(N x log(N)) + O(Q x log(N)) ~ O((N + Q) x log(N))
     * SC: O(1)
     */
    public ArrayList<Integer> cntInRange(int[] arr, int[][] queries) {
        int n = arr.length;
        Arrays.sort(arr); // TC: O(N x log(N))
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int[] query : queries) { // TC: O(Q)
            int count = 
                upperBound(arr, n, query[1]) - 
                lowerBound(arr, n, query[0]); // TC: O(2 x log(N))
            result.add(count);
        }
        return result;
    }
    
    /**
     * Using Binary Search (To Find Lower Bound) Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int lowerBound(int[] arr, int n, int x) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    /**
     * Using Binary Search (To Find Upper Bound) Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int upperBound(int[] arr, int n, int x) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
