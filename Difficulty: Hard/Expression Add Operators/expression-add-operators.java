class Solution {
    /**
     * Approach : Using Recursion + Backtracking Approach
     * 
     * TC: O(N x 3 ^ N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public ArrayList<String> findExpr(String s, int target) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        ArrayList<String> result = new ArrayList<String>();
        solveRecursion(s, target, 0L, 0L, 0, n, sb, result); // TC: O(N x 3 ^ N), SC: O(N)
        return result;
    }

    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(N x 3 ^ N)
     * SC: O(N)
     */
    private void solveRecursion(String s, int target, long currentValue, long last,
        int idx, int n, StringBuilder sb, ArrayList<String> result) {
        // Base Case
        if (idx == n) {
            if (currentValue == target) {
                result.add(sb.toString());
                return;
            }
            return;
        }
        // Recursion
        for (int i = idx; i < n; i++) { // TC: O(N)
            // skip leading zeros while creating multiple digit number like '05'
            if (i != idx && s.charAt(idx) == '0') {
                break;
            }
            long cur = Long.parseLong(s.substring(idx, i + 1));
            int len = sb.length();
            if (idx == 0) {
                // just append the value to 'sb'
                // modify
                sb.append(cur);
                // explore
                solveRecursion(s, target, cur, cur, i + 1, n, sb, result);
                // backtrack
                sb.setLength(len);
            } else {
                // try addition '+' modify
                sb.append('+').append(cur);
                // explore
                solveRecursion(s, target, currentValue + cur, cur, i + 1, n, sb, result);
                // backtrack
                sb.setLength(len);
                
                // try subtraction '-' modify
                sb.append('-').append(cur);
                // explore
                solveRecursion(s, target, currentValue - cur, -cur, i + 1, n, sb, result);
                // backtrack
                sb.setLength(len);
                
                // try multiplication '*' modify
                sb.append('*').append(cur);
                // explore
                solveRecursion(s, target, currentValue - last + last * cur, last * cur, i + 1, n, sb, result);
                // backtrack
                sb.setLength(len);
            }
        }
    }
}
