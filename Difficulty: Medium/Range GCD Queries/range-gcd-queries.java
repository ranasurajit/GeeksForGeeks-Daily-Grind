// User function Template for Java
/**
 * Approach : Using Segment Tree Approach
 * 
 * TC: O(Q x log(K) x log(N)) + O(log(N) for Q queries and O(log(N)) for each updation
 * SC: O(log(N)) - recursion stack space
 * 
 * where K = Minimum value in array 'arr'
 */
class Solution {
    // Function to find gcd of given range.
    /**
     * Using Segment Tree Approach
     * 
     * TC: O(2 x log(K) x log(N)) ~ O(log(K) x log(N))
     * SC: O(log(N))
     * 
     * traversed height of binary tree twice
     */
    public static int findRangeGcd(int l, int r, int st[], int n) {
        return querySegmentTree(l, r, st, n, 0, 0, n - 1);
    }

    // Function to update a value in input array and segment tree.
    /**
     * Using Segment Tree Approach
     * 
     * TC: O(log(K) x log(N))
     * SC: O(log(N))
     */
    public static void updateValue(int index, int new_val, int arr[], int st[], int n) {
        updateSegmentTree(index, new_val, arr, st, n, 0, 0, n - 1);
    }
    
    /**
     * Using Segment Tree Approach
     * 
     * TC: O(2 x log(K) x log(N)) ~ O(log(K) x log(N))
     * SC: O(log(N))
     * 
     * traversed height of binary tree twice
     */
    private static int querySegmentTree(int left, int right, int[] st, int n,
        int idx, int low, int high) {
        // Base Case
        // case 1: no overlap
        if (left > high || right < low) {
            return 0;
        }
        // case 2: full overlap
        if (low >= left && high <= right) {
            return st[idx];
        }
        // case 3: partial overlap
        // Recursion Calls - get values from both left and right children
        int mid = low + (high - low) / 2;
        int leftGCD = querySegmentTree(left, right, st, n, 2 * idx + 1, low, mid);
        int rightGCD = querySegmentTree(left, right, st, n, 2 * idx + 2, mid + 1, high);
        return gcd(leftGCD, rightGCD); // TC: O(log(K))
    }
    
    /**
     * Using Segment Tree Approach
     * 
     * TC: O(log(K) x log(N))
     * SC: O(log(N))
     */
    private static void updateSegmentTree(int index, int new_val, 
        int[] arr, int[] st, int n, int idx, int low, int high) {
        // Base Case
        if (low == high) {
            st[idx] = new_val;
            arr[index] = new_val;
            return;
        }
        // Recursion Calls
        int mid = low + (high - low) / 2;
        if (index <= mid) {
            updateSegmentTree(index, new_val, arr, st, n, 2 * idx + 1, low, mid);
        } else {
            updateSegmentTree(index, new_val, arr, st, n, 2 * idx + 2, mid + 1, high);
        }
        st[idx] = gcd(st[2 * idx + 1], st[2 * idx + 2]); // TC: O(log(K))
    }
    
    /**
     * Using Euclidean GCD algorithm
     * 
     * TC: O(log(Min(a, b)))
     * SC: O(log(Min(a, b)))
     */
    private static int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
