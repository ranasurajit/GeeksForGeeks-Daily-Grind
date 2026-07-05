class Solution {
    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC : O(m + n)
     * SC : O(1)
     */
    public int maxPathSum(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int p = 0;
        int q = 0;
        int maxSum = 0;
        int sumA = 0;
        int sumB = 0;
        while (p < m && q < n) { // TC : O(m + n)
            if (a[p] < b[q]) {
                sumA += a[p];
                p++;
            } else if (a[p] > b[q]) {
                sumB += b[q];
                q++;
            } else {
                // here a[p] == b[q]
                maxSum += Math.max(sumA, sumB) + a[p];
                // reset sumA and sumB to again accumulate
                sumA = 0;
                sumB = 0;
                p++;
                q++;
            }
        }
        while (p < m) {
            sumA += a[p];
            p++;
        }
        while (q < n) {
            sumB += b[q];
            q++;
        }
        maxSum += Math.max(sumA, sumB);
        return maxSum;
    }
}
