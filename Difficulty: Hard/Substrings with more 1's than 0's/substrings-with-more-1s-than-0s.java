class Solution {
    /**
     * Approach III : Using Optimal (Segment Tree) Approach
     * 
     * TC : O(n) + O(n x log(n)) ~ O(n x log(n))
     * SC : O(n) + O(n) ~ O(n)
     * 
     * Accepted (135 / 135 testcases passed)
     */
    public int countSubstring(String s) {
        int n = s.length();
        /**
         * as we need Substrings with 1 dominance so, 
         * we may replace the 0s with -1 and 1s with +1
         * and we need to find the prefix-sum of that
         * post replacement
         */
        int[] prefixSum = new int[n + 1]; // SC : O(n)
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            prefixSum[i + 1] = prefixSum[i] + (s.charAt(i) == '0' ? -1 : 1);
        }
        /**
         * we need to basically find the count of left (l) indices for 
         * every right (r) indices where prefixSum[r] > prefixSum[l] and
         * we can do this efficiently using a Segment Tree but we need to
         * shift the prefixSum by 'n' as it can have maximum negative 
         * value of -n and it will not create segment tree unless shifted
         */
        int m = 2 * n + 1; // as range of prefixSum can be [-n, n]
        int[] segTree = new int[4 * m]; // SC : O(n)
        int shift = n;
        int count = 0;
        for (int i = 0; i <= n; i++) { // TC : O(n)
            int idx = prefixSum[i] + shift;
            if (idx > 0) {
                count += query(0, idx - 1, segTree, m); // TC : O(log(n)), SC : O(log(n))
            }
            update(idx, segTree, m);  // TC : O(log(n)), SC : O(log(n))
        }
        return count;
    }
    
    /**
     * Using Segment Tree Point Update Approach
     * 
     * TC : O(log(m))
     * SC : O(log(m))
     */
    private void update(int index, int[] segTree, int size) {
        updateSegmentTree(index, segTree, 0, 0, size - 1);
    }
    
    
    /**
     * Using Segment Tree Approach
     * 
     * TC : O(log(m))
     * SC : O(log(m))
     */
    private int query(int start, int end, int[] segTree, int size) {
        return querySegmentTree(start, end, segTree, 0, 0, size - 1);
    }
    
    /**
     * Using Segment Tree Point Update Approach
     * 
     * TC : O(log(m))
     * SC : O(log(m))
     */
    private void updateSegmentTree(int index, int[] segTree,
        int idx, int l, int r) {
        // Base Case
        if (l == r) {
            segTree[idx]++;
            return;
        }
        // Recursion Calls
        int mid = l + (r - l) / 2;
        if (index <= mid) {
            updateSegmentTree(index, segTree, 2 * idx + 1, l, mid);
        } else {
            updateSegmentTree(index, segTree, 2 * idx + 2, mid + 1, r);
        }
        segTree[idx] = segTree[2 * idx + 1] + segTree[2 * idx + 2];
    }
    
    /**
     * Using Segment Tree Approach
     * 
     * TC : O(log(m))
     * SC : O(log(m))
     */
    private int querySegmentTree(int start, int end, int[] segTree,
        int idx, int l, int r) {
        // Base Case
        // Case 1 : No Overlap
        if (end < l || start > r) {
            return 0;
        }
        // Case 2 : Full Overlap
        if (l >= start && r <= end) {
            return segTree[idx];
        }
        // Case 3 : Partial Overlap
        // Recursion Calls
        int mid = l + (r - l) / 2;
        int left =
            querySegmentTree(start, end, segTree, 2 * idx + 1, l, mid);
        int right =
            querySegmentTree(start, end, segTree, 2 * idx + 2, mid + 1, r);
        return left + right;
    }

    /**
     * Approach II : Using Better (String Simulation) Approach
     * 
     * TC : O(n) + O(n²) ~ O(n²)
     * SC : O(n)
     * 
     * Time Limit Exceeded (123 / 135 testcases passed)
     */
    public int countSubstringBetter(String s) {
        int n = s.length();
        /**
         * as we need Substrings with 1 dominance so, 
         * we may replace the 0s with -1 and 1s with +1
         * and we need to find the prefix-sum of that
         * post replacement
         */
        int[] prefixSum = new int[n + 1]; // SC : O(n)
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            prefixSum[i + 1] = prefixSum[i] + (s.charAt(i) == '0' ? -1 : 1);
        }
        int count = 0;
        /**
         * we need to basically find the count of left (l) indices for 
         * every right (r) indices where prefixSum[r] > prefixSum[l]
         */
        for (int l = 0; l <= n; l++) { // TC : O(n)
            for (int r = l + 1; r <= n; r++) { // TC : O(n)
                if (prefixSum[r] > prefixSum[l]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Approach I : Using Brute-Force (String Simulation) Approach
     * 
     * TC : O(n²)
     * SC : O(1)
     * 
     * Time Limit Exceeded (113 / 135 testcases passed)
     */
    public int countSubstringBruteForce(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            int zeroes = 0;
            int ones = 0;
            for (int j = i; j < n; j++) { // TC : O(n)
                zeroes += s.charAt(j) == '0' ? 1 : 0;
                ones += s.charAt(j) == '1' ? 1 : 0;
                if (ones > zeroes) {
                    count++;
                }
            }
        }
        return count;
    }
}
