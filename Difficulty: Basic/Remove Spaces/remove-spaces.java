class Solution {
    /**
     * Approach : Using String Simulation Approach
     * 
     * TC: O(n)
     * SC: O(n)
     */
    String removeSpaces(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(); // SC: O(n)
        for (int i = 0; i < n; i++) { // TC: O(n)
            char ch = s.charAt(i);
            if (ch != ' ') {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
