class Solution {
    /**
     * Approach : Using String Simulation Approach
     * 
     * TC: O(n)
     * SC: O(n)
     */
    String URLify(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(); // SC: O(n)
        for (int i = 0; i < n; i++) { // TC: O(n)
            char ch = s.charAt(i);
            if (ch != ' ') {
                sb.append(ch);
            } else {
                sb.append("%20");
            }
        }
        return sb.toString();
    }
}
