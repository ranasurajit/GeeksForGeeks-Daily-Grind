class Solution {
    private int n;
    private int[] arr;
    private long[] segTree;

    /**
     * Approach : Using Segment Tree Approach
     * 
     * TC: O(N) + O(N) + O(Q x log(N)) ~ O(Q x log(N)) - travels through each node
     * SC: O(4 x N) + O(log(N)) ~ O(N) + O(log(N)) - recursion stack
     */
    public ArrayList<Integer> specialSum(int[] arr, int[][] queries) {
        this.arr = arr;
        this.n = arr.length;
        this.segTree = new long[4 * n]; // SC: O(4 x N)
        buildSegmentTree(0, 0, n - 1);  // TC: O(N), SC: O(log(N))
        long totalSum = 0L;
        for (int i = 0; i < n; i++) {   // TC: O(N)
            totalSum += (long) arr[i];
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int[] query : queries) {   // TC: O(Q)
            int left = query[0];
            int right = query[1];
            long diff = totalSum -
                querySegmentTree(left, right, 0, 0, n - 1); // TC: O(log(N))
            result.add((int) diff);
        }
        return result;
    }

    /**
     * Using Segment Tree Approach
     * 
     * TC: O(N) - travels through each node
     * SC: O(log(N)) - recursion stack
     */
    private void buildSegmentTree(int idx, int low, int high) {
        // Base Case
        if (low == high) {
            segTree[idx] = arr[low];
            return;
        }
        // Recursion Calls
        int mid = low + (high - low) / 2;
        buildSegmentTree(2 * idx + 1, low, mid);
        buildSegmentTree(2 * idx + 2, mid + 1, high);
        segTree[idx] = segTree[2 * idx + 1] + segTree[2 * idx + 2];
    }

    /**
     * Using Segment Tree Approach
     * 
     * TC: O(2 x log(N)) ~ O(log(N)) - traverses twice the height of Binary Tree
     * SC: O(log(N)) - recursion stack
     */
    private long querySegmentTree(int left, int right, int idx, int low, int high) {
        // Base Case
        // case 1 : no overlap
        if (left > high || right < low) {
            return 0L;
        }
        // case 2 : full overlap
        if (low >= left && high <= right) {
            return segTree[idx];
        }
        // Recursion Calls : partial overlap
        int mid = low + (high - low) / 2;
        return querySegmentTree(left, right, 2 * idx + 1, low, mid) +
            querySegmentTree(left, right, 2 * idx + 2, mid + 1, high);
    }
}
