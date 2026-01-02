/* The functions which
builds the segment tree */
class GfG {
    static int st[];

    /**
     * Using Segment Tree Approach
     * 
     * TC: O(N)
     * SC: O(N) + O(log(N)) - recursion stack
     */
    public static int[] constructST(int arr[], int n) {
        st = new int[4 * n]; // SC: O(4 x N) ~ O(N)
        buildSegmentTree(arr, 0, 0 , n - 1);
        return st;
    }

    /**
     * Using Segment Tree Approach
     * 
     * TC: O(2 x log(N)) ~ O(log(N)) - traverse twice of height of segment tree
     * SC: O(log(N)) - recursion stack
     */
    /* The functions returns the
      min element in the range
      from l and r */
    public static int RMQ(int st[], int n, int l, int r) {
        return querySegmentTree(st, n, l, r, 0, 0, n - 1);
    }
    
    /**
     * Using Segment Tree Approach
     * 
     * TC: O(N)
     * SC: O(log(N)) - recursion stack
     */
    private static void buildSegmentTree(int[] arr, int idx, int low, int high) {
        // Base Case
        if (low == high) {
            st[idx] = arr[low];
            return;
        }
        // Recursion Calls
        int mid = low + (high - low) / 2;
        buildSegmentTree(arr, 2 * idx + 1, low, mid);
        buildSegmentTree(arr, 2 * idx + 2, mid + 1, high);
        st[idx] = Math.min(st[2 * idx + 1], st[2 * idx + 2]);
    }
    
    /**
     * Using Segment Tree Approach
     * 
     * TC: O(2 x log(N)) ~ O(log(N)) - traverse twice of height of segment tree
     * SC: O(log(N)) - recursion stack
     */
    private static int querySegmentTree(int[] st, int n,
        int left, int right, int idx, int low, int high) {
        // Base Case
        // case 1: no-overlap
        if (right < low || left > high) {
            return Integer.MAX_VALUE;
        }
        // case 2: full-overlap
        if (low >= left && high <= right) {
            return st[idx];
        }
        // case 3: partial-overlap
        // Recursion Calls
        int mid = low + (high - low) / 2;
        return Math.min(
            querySegmentTree(st, n, left, right, 2 * idx + 1, low, mid),
            querySegmentTree(st, n, left, right, 2 * idx + 2, mid + 1, high)
        );
    } 
}
