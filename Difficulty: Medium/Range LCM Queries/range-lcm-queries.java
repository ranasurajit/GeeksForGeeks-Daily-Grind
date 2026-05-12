class Solution {
    private int n;
    private long[] segTree;

    /**
     * Approach : Using Segment Tree Range Query Update Approach
     * 
     * TC : O(n) + O(q x log(n)) ~ O(n + q x log(n)) 
     * SC : O(n) + O(n) + O(q x log(n)) ~ O(n + q x log(n)) 
     */
    public ArrayList<Long> RangeLCMQuery(int[] arr, int[][] queries) {
        int n = arr.length;
        ArrayList<Long> result = new ArrayList<>();
        segTree = new long[4 * n]; // SC : O(4 x n) ~ O(n)
        buildSegmentTree(arr, 0, 0, n - 1); // TC : O(n), SC : O(n)
        for (int[] query : queries) { // TC : O(q)
            int type = query[0];
            if (type == 1) {
                // update query on Segment Tree
                int index = query[1];
                int value = query[2];
                updateSegmentTree(index, value, 0, 0, n - 1); // TC : O(log(n))
            } else {
                // range query on Segment Tree
                int start = query[1];
                int end = query[2];
                long queryResult = 
                    querySegmentTree(0, 0, n - 1, start, end); // TC : O(log(n))
                result.add(queryResult);
            }
        }
        return result;
    }
    
    /**
     * Using Segment Tree Build Approach
     * 
     * TC : O(n)
     * SC : O(log(n))
     */
    private void buildSegmentTree(int[] arr, int idx, int l, int r) {
        // Base Case
        if (l == r) {
            segTree[idx] = arr[l];
            return;
        }
        // Recursion Calls
        int mid = l + (r - l) / 2;
        buildSegmentTree(arr, 2 * idx + 1, l, mid);     // left
        buildSegmentTree(arr, 2 * idx + 2, mid + 1, r); // right
        segTree[idx] = getLCM(segTree[2 * idx + 1], segTree[2 *idx + 2]);
    }
    
    /**
     * Using Segment Tree Update Approach
     * 
     * TC : O(log(n))
     * SC : O(log(n))
     */
    private void updateSegmentTree(int index, int value, int idx, int l, int r) {
        // Base Case
        if (l == r) {
            segTree[idx] = value;
            return;
        }
        // Recursion Calls
        int mid = l + (r - l) / 2;
        if (index <= mid) {
            // we need to update in the left sub-tree of Segment Tree
            updateSegmentTree(index, value, 2 * idx + 1, l, mid);
        } else {
            // we need to update in the right sub-tree of Segment Tree
            updateSegmentTree(index, value, 2 * idx + 2, mid + 1, r);
        }
        segTree[idx] = getLCM(segTree[2 * idx + 1], segTree[2 * idx + 2]);
    }
    
    /**
     * Using Segment Tree Query Approach
     * 
     * TC : O(log(n))
     * SC : O(log(n))
     */
    private long querySegmentTree(int idx, int l, int r, int start, int end) {
        // Base Case
        // no overlap
        if (end < l || start > r) {
            return 1L;
        }
        // full overlap
        if (start <= l && end >= r) {
            return segTree[idx];
        }
        // Recursion Calls
        int mid = l + (r - l) / 2;
        return getLCM(
            querySegmentTree(2 * idx + 1, l, mid, start, end),
            querySegmentTree(2 * idx + 2, mid + 1, r, start, end)
        );
    }

    /**
     * Using Math Approach
     * 
     * TC : O(log(Min(a, b)))
     * SC : O(log(Min(a, b)))
     */
    private long getLCM(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
    
    /**
     * Using Math Approach
     * 
     * TC : O(log(Min(a, b)))
     * SC : O(log(Min(a, b)))
     */
    private long gcd(long a, long b) {
        // Base Case
        if (b == 0) {
            return a;
        }
        // Recursion Calls
        return gcd(b, a % b);
    }
}
