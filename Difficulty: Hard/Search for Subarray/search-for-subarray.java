class Solution {
    /**
     * Approach II : Using Optimal (KMP Algorithm) Approach
     * 
     * TC : O(n) + O(m + n) ~ O(m + n)
     * SC : O(n)
     * - O(n) - lps array construction
     * 
     * Accepted (1112 / 1112 testcases passed)
     */
    public ArrayList<Integer> search(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[] lps = new int[n]; // SC : O(n)
        computeLPS(b, n, lps);  // TC : O(n), SC : O(1)
        int i = 0; // pointer at the start of array 'a'
        int j = 0; // pointer at the start of array 'b'
        ArrayList<Integer> result = new ArrayList<>();
        while (i < m) { // TC : O(m + n)
            if (a[i] == b[j]) {
                i++;
                j++;
                if (j == n) {
                    // we found a match so we need to store starting index of match
                    result.add(i - n);
                    // continue doing the search
                    j = lps[j - 1]; // resets pointer 'j'
                }
            } else {
                // we need to fallback 'j' pointer
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return result;
    }

    /**
     * Using KMP Algorithm Approach to Find LPS array
     * 
     * TC : O(n)
     * SC : O(1)
     */
    private void computeLPS(int[] b, int n, int[] lps) {
        int len = 0; // pointer where prefix-suffix last matched
        int i = 1;
        while (i < n) { // TC : O(n)
            if (b[len] == b[i]) {
                len += 1;
                lps[i] = len;
                i++;
            } else {
                // we need to fallback len pointer
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    i++;
                }
            }
        }
    }

    /**
     * Approach I : Using Brute-Force (Simulation + Two Pointers) Approach
     * 
     * TC : O(m x n)
     * SC : O(1)
     * 
     * Time Limit Exceeded (1110 / 1112 testcases passed)
     */
    public ArrayList<Integer> searchBruteForce(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int target = b[0];
        /**
         * We will try to get match every element of
         * array 'b' in array 'a' from every index
         * it matches the target
         */
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < m; i++) { // TC : O(m)
            if (a[i] == target) {
                int p = i; // pointer at the match point of array 'a'
                int q = 0; // pointer at the start point of array 'b'
                while (p < m && q < n) { // TC : O(n)
                    if (a[p] == b[q]) {
                        p++;
                        q++;
                    } else {
                        break;
                    }
                }
                if (q == n) {
                    result.add(i);
                }
            }
        }
        return result;
    }
}
