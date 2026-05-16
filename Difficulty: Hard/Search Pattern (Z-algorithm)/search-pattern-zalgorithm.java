class Solution {
    /**
     * Approach : Using Z-Algorithm Approach
     * 
     * TC : O(m + n) + O(m + n) ~ O(m + n)
     * SC : O(m + n)
     */
    public static ArrayList<Integer> search(String txt, String pat) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = txt.length();
        int m = pat.length();
        String s = pat + "$" + txt;
        int[] z = new int[m + n + 1]; // SC : O(m + n)
        constructZArray(z, s.toCharArray(), m + n + 1); // TC : O(m + n), SC : O(1)
        for (int i = 0; i < m + n + 1; i++) { // TC : O(m + n)
            if (z[i] == m) {
                result.add(i - m - 1);
            }
        }
        return result;
    }

    /**
     * Using Z-Algorithm Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    private static void constructZArray(int[] z, char[] input, int n) {
        z[0] = 0;
        int left = 0;
        int right = 0;
        for (int k = 1; k < n; k++) { // TC : O(n)
            if (k > right) {
                left = k;
                right = k;
                while (right < n && input[right] == input[right - left]) {
                    right++;
                }
                z[k] = right - left;
                right--;
            } else {
                int k1 = k - left;
                if (z[k1] < right - k + 1) {
                    z[k] = z[k1];
                } else {
                    // we need to check and compare again
                    left = k;
                    while (right < n && input[right] == input[right - left]) {
                        right++;
                    }
                    z[k] = right - left;
                    right--;
                }
            }
        }
    }
}
