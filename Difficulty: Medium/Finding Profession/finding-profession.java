class Solution {
    /**
     * Approach II : Using Bit-Manipulation Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public String profession(int level, int pos) {
        /**
         * for any level 1st half of that level is
         * same as previous levels patten and 2nd
         * half position should be toggled
         */
        int n = pos - 1;
        int bitCount = Integer.bitCount(n);
        if ((bitCount & 1) == 0) {
            // even set bits
            return "Engineer";
        } else {
            // even set bits
            return "Doctor";
        }
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(level)
     * SC : O(level)
     */
    public String professionRecursion(int level, int pos) {
        return solve(level, pos);
    }
    
    /**
     * Using Recursion Approach
     *
     * TC : O(level)
     * SC : O(level)
     */
    private String solve(int level, int pos) {
        // Base Case
        if (level == 1 && pos == 1) {
            return "Engineer";
        }
        // Recursion Calls
        boolean isEven = (pos & 1) == 0;
        int parentPos = isEven ? pos / 2 : (pos + 1) / 2;
        int parentLevel = level - 1;
        String parent = solve(parentLevel, parentPos);
        String child = null;
        if (parent.equals("Engineer")) {
            if (isEven) {
                child = "Doctor";
            } else {
                child = "Engineer";
            }
        } else {
            if (isEven) {
                child = "Engineer";
            } else {
                child = "Doctor";
            }
        }
        return child;
    }
}
