class Solution {
    private ArrayList<String> result;
    private int n;

    /**
     * Approach : Using Recursion + Backtracking Approach
     * 
     * TC: O(3⁴)
     * SC: O(4) + O(4) ~ O(1)
     * - O(4) - StringBuilder memory
     * - O(4) - recursion stack
     */
    public ArrayList<String> generateIp(String s) {
        result = new ArrayList<String>();
        this.n = s.length();
        backtrack(0, 0, new StringBuilder(), s);
        return result;
    }
    
    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(3⁴)
     * SC: O(4) + O(4)
     */
    private void backtrack(int idx, int dotsUsed, StringBuilder path, String s) {
        // Base Case
        if (dotsUsed == 3) {
            if (idx >= n) {
                return;
            }
            String segment = s.substring(idx);
            if (isSegmentValid(segment)) {
                result.add(path.toString() + segment);
            }
            return;
        }
        // Recursion Calls
        for (int len = 1; len <= 3; len++) { // TC: O(3)
            if (idx + len > n) {
                break;
            }
            String segment = s.substring(idx, idx + len);
            if (!isSegmentValid(segment)) {
                continue;
            }
            int prevLen = path.length();
            path.append(segment).append('.'); // modify
            backtrack(idx + len, dotsUsed + 1, path, s); // explore
            path.setLength(prevLen); // backtrack
        }
    }
    
    /**
     * Using Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isSegmentValid(String segment) {
        if (segment.length() == 0 || segment.length() > 3) {
            return false;
        }
        if (segment.length() > 1 && segment.charAt(0) == '0') {
            return false;
        }
        int segmentVal = Integer.valueOf(segment);
        return segmentVal >= 0 && segmentVal <= 255;
    }
}
