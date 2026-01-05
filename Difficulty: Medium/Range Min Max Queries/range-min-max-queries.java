/**
 * Approach : Using Segment Trees Approach
 * 
 * TC: O(log(N)) to update and O(Q x log(N)) to perform all Q queries
 * SC: O(log(N)) - recursion stack space
 */
class Solution {
    // Returns a vector<int> of size 2 where:
    // [0] = minimum value in arr from index L to R (inclusive),
    // [1] = maximum value in arr from index L to R (inclusive).
    // Uses the prebuilt segTree where each node stores [min, max].
    // Segment tree indexing:
    // - For a node at idx, left child is at 2*idx + 1, right child at 2*idx + 2.
    /**
     * Using Segment Trees Approach
     * 
     * TC: O(2 x log(N)) ~ O(log(N))
     * SC: O(log(N))
     */
    public int[] getMinMax(int[] arr, int L, int R, int[][] segTree) {
        return querySegmentTree(arr, L, R, segTree, 0, 0, arr.length - 1);
    }

    // Updates the value at arr[index] to 'value' and updates the segTree accordingly.
    // Uses the prebuilt segTree where each node stores [min, max].
    // Segment tree indexing:
    // - For a node at idx, left child is at 2*idx + 1, right child at 2*idx + 2.
    /**
     * Using Segment Trees Approach
     * 
     * TC: O(log(N))
     * SC: O(log(N))
     */
    public void updateValue(int[] arr, int index, int value, int[][] segTree) {
        updateSegmentTree(arr, index, value, segTree, 0, 0, arr.length - 1);
    }
    
    /**
     * Using Segment Trees Approach
     * 
     * TC: O(2 x log(N)) ~ O(log(N))
     * SC: O(log(N))
     */
    private int[] querySegmentTree(int[] arr, int L, int R,
        int[][] segTree, int idx, int low, int high) {
        // Base Case
        // case 1: no-overlap
        if (high < L || low > R) {
            // return invalid values
            return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE };   
        }
        // case 2: full overlap
        if (low >= L && high <= R) {
            return segTree[idx];
        }
        // Recursion Calls
        // case 3: partial overlap - get the answer from both side children
        int mid = low + (high - low) / 2;
        int[] left = querySegmentTree(arr, L, R, segTree, 2 * idx + 1, low, mid);
        int[] right = querySegmentTree(arr, L, R, segTree, 2 * idx + 2, mid + 1, high);
        return new int[] { Math.min(left[0], right[0]), Math.max(left[1], right[1]) };
    }
    
    /**
     * Using Segment Trees Approach
     * 
     * TC: O(log(N))
     * SC: O(log(N))
     */
    private void updateSegmentTree(int[] arr, int index, int value,
        int[][] segTree, int idx, int low, int high) {
        // Base Case
        if (low == high) {
            segTree[idx] = new int[] { value, value };
            arr[index] = value;
            return;
        }
        // Recursion Calls
        int mid = low + (high - low) / 2;
        if (index <= mid) {
            updateSegmentTree(arr, index, value, segTree, 2 * idx + 1, low, mid);
        } else {
            updateSegmentTree(arr, index, value, segTree, 2 * idx + 2, mid + 1, high);
        }
        segTree[idx][0] = Math.min(segTree[2 * idx + 1][0], segTree[2 * idx + 2][0]);
        segTree[idx][1] = Math.max(segTree[2 * idx + 1][1], segTree[2 * idx + 2][1]);
    }
};
