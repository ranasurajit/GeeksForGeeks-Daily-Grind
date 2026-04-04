class Solution {
    /**
     * Approach : Using Recursion Approach
     * 
     * TC: O(n x 2ⁿ)
     * SC: O(n x 2ⁿ)
     */
    public ArrayList<String> graycode(int n) {
        ArrayList<String> result = new ArrayList<>();
        // Base Case
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        // Recursion Calls
        ArrayList<String> prev = graycode(n - 1);
        // prepend '0' to each item returned from previous recursion of (n - 1)
        for (String s : prev) {
            result.add("0" + s);
        }
        for (int i = prev.size() - 1; i >= 0; i--) { // TC: O(N)
            result.add("1" + prev.get(i));
        }
        return result;
    }
}
