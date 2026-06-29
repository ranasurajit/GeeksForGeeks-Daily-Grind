class Solution {
    private int n;
    private long[] segTree;

    /**
     * Approach : Using Segment Tree Approach
     * 
     * TC : O(n) + O(q x log(n)) ~ O(n + q x log(n))
     * SC : O(n) + O(log(n)) + O(log(n)) ~ O(n)
     */
    public ArrayList<Long> RangeLCMQuery(int[] arr, int[][] queries) {
        this.n = arr.length;
        this.segTree = new long[4 * n]; // SC : O(4 x n) ~ O(n)
        buildSegmentTree(0, 0, n - 1, arr); // TC : O(n), SC : O(log(n))
        ArrayList<Long> result = new ArrayList<>();
        for (int[] query : queries) { // TC : O(q)
            int type = query[0];
            if (type == 1) {
                int index = query[1];
                int val = query[2];
                updateSegmentTree(0, 0, n - 1, index, val); // TC : O(log(n)), SC : O(log(n))
            } else {
                int start = query[1];
                int end = query[2];
                long value = 
                    querySegmentTree(0, 0, n - 1, start, end); // TC : O(log(n)), SC : O(log(n))
                result.add(value);
            }
        }
        return result;
    }
    
    /**
     * Using Segment Tree Approach
     * 
     * TC : O(log(n))
     * SC : O(log(n))
     */
    private void updateSegmentTree(int idx, int l, int r, int index, int value) {
        // Base Case
        if (l == r) {
            segTree[idx] = value;
            return;
        }
        // Recursion Calls
        int mid = l + (r - l) / 2;
        if (index <= mid) {
            updateSegmentTree(2 * idx + 1, l, mid, index, value);
        } else {
            updateSegmentTree(2 * idx + 2, mid + 1, r, index, value);
        }
        segTree[idx] = getLCM(segTree[2 * idx + 1], segTree[2 * idx + 2]);
    }
    
    /**
     * Using Segment Tree Approach
     * 
     * TC : O(log(n))
     * SC : O(log(n))
     */
    private long querySegmentTree(int idx, int l, int r, int start, int end) {
        // Base Case
        // Case 1 : No Overlap
        if (end < l || start > r) {
            return 1L;
        }
        // Case 2 : Full Overlap
        if (l >= start && r <= end) {
            return segTree[idx];
        }
        // Recursion Calls
        // Case 3 : Partial Overlap
        int mid = l + (r - l) / 2;
        long left = querySegmentTree(2 * idx + 1, l, mid, start, end);
        long right = querySegmentTree(2 * idx + 2, mid + 1, r, start, end);
        return getLCM(left, right);
    }
    
    /**
     * Using Segment Tree Approach
     * 
     * TC : O(n) + O(log(n))
     * SC : O(log(n)) + O(log(n)) ~ O(log(n))
     */
    private void buildSegmentTree(int idx, int l, int r, int[] arr) {
        // Base Case
        if (l == r) {
            segTree[idx] = (long) arr[l];
            return;
        }
        // Recursion Calls
        int mid = l + (r - l) / 2;
        buildSegmentTree(2 * idx + 1, l, mid, arr);
        buildSegmentTree(2 * idx + 2, mid + 1, r, arr);
        segTree[idx] = getLCM(segTree[2 * idx + 1], segTree[2 * idx + 2]);
    }

    /**
     * Using Math Approach
     * 
     * TC : O(log(Min(a, b)))
     * SC : O(log(Min(a, b)))
     */
    private long getLCM(long a, long b) {
        long prod = a * b;
        long gcd = computeGCD(a, b); // TC : O(log(Min(a, b))), SC : O(log(Min(a, b)))
        return prod / gcd;
    }
    
    /**
     * Using Math Approach
     * 
     * TC : O(log(Min(a, b)))
     * SC : O(log(Min(a, b)))
     */
    private long computeGCD(long a, long b) {
        if (b == 0) {
            return a;
        }
        if (b > a) {
            return computeGCD(b, a);
        }
        return computeGCD(b, a % b);
    }
}
