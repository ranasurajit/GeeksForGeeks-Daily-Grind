class Solution {
    private static char[][] keypad = {
        new char[] {},
        new char[] {},
        { 'a', 'b', 'c' },
        { 'd', 'e', 'f' },
        { 'g', 'h', 'i' },
        { 'j', 'k', 'l' },
        { 'm', 'n', 'o' },
        { 'p', 'q', 'r', 's' },
        { 't', 'u', 'v' },
        { 'w', 'x', 'y', 'z' }
    };

    /**
     * Approach : Using Recursion + Backtracking Approach
     * 
     * TC: O(4 ^ N)
     * SC: O(N)
     */
    public ArrayList<String> possibleWords(int[] arr) {
        int n = arr.length;
        ArrayList<String> result = new ArrayList<String>();
        solveRecursion(0, n, arr, new StringBuilder(), result); // TC: O(4 ^ N), SC: O(N)
        return result;
    }
    
    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(4 ^ N)
     * SC: O(N) + O(N) ~ O(N)
     */
    private void solveRecursion(int idx, int n, int[] arr,
        StringBuilder sb, ArrayList<String> result) {
        // Base Case
        if (idx == n) {
            result.add(new String(sb.toString()));
            return;
        }
        // Recursion Calls
        char[] currentKeys = keypad[arr[idx]];
        if (currentKeys.length == 0) {
            // arr[idx] is 0 or 1 so move further to next index
            solveRecursion(idx + 1, n, arr, sb, result); // explore
            return;
        }
        for (char ch : currentKeys) { // TC: O(4)
            sb.append(ch); // modify
            solveRecursion(idx + 1, n, arr, sb, result); // explore
            sb.setLength(sb.length() - 1); // backtrack
        }
    }
}
