class Solution {
    /**
     * Approach : Using BitManipulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public static ArrayList<Integer> subsetXOR(int n) {
        int xorAll = 0;
        ArrayList<Integer> subsets = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) { // TC: O(N)
            xorAll ^= i;
        }
        if (xorAll == n) {
            for (int i = 1; i <= n; i++) { // TC: O(N)
                subsets.add(i);
            }
        } else {
            /**
             * we need to remove a number and check it with xorAll
             * 
             * say we remove x from xorAll ^ x = n so, 
             * xorAll ^ (xorAll ^ x) = xorAll ^ n i.e.
             * x = xorAll ^ n
             */
            int x = xorAll ^ n;
            for (int i = 1; i <= n; i++) { // TC: O(N)
                if (x != i) {
                    subsets.add(i);
                }
            }
        }
        return subsets;
    }
}
