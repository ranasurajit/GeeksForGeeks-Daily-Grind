class Solution {
    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(Q x log(N))
     * SC: O(1)
     */
    public ArrayList<Integer> countXInRange(int[] arr, int[][] queries) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int[] query : queries) { // TC: O(Q)
            int l = query[0];
            int r = query[1];
            int x = query[2];
            result.add(upperBound(arr, l, r, x) -
                lowerBound(arr, l, r, x)); // TC: O(2 x log(N))
        }
        return result;
    }
    
    /**
     * Using Binary Search (Lower Bound) Approach
     * 
     * TC: O(log(R)), where R = high - low
     * SC: O(1)
     */
    private int lowerBound(int[] arr, int low, int high, int x) {
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
     * Using Binary Search (Upper Bound) Approach
     * 
     * TC: O(log(R)), where R = high - low
     * SC: O(1)
     */
    private int upperBound(int[] arr, int low, int high, int x) {
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
