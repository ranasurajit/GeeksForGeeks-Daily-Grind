class Solution {
    /**
     * Approach II : Using Two Pointers Approach
     * 
     * TC: O(n)
     * SC: O(n)
     */
    String removeSpaces(String s) {
        char[] chars = s.toCharArray(); // SC: O(n)
        int n = chars.length;
        int write = 0;
        for (int read = 0; read < n; read++) {
            if (chars[read] != ' ') {
                chars[write++] = chars[read];
            }
        }
        return new String(chars, 0, write);
    }

    /**
     * Approach I : Using String Simulation Approach
     * 
     * TC: O(n)
     * SC: O(n)
     */
    String removeSpacesStringSimulation(String s) {
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
