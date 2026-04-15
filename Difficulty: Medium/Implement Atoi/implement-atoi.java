class Solution {
    /**
     * Approach : Using String Simulation Approach
     * 
     * TC: O(n)
     * SC: O(1)
     */
    public int myAtoi(String s) {
        int n = s.length();
        int index = 0;
        // clean-up all white-spaces or leading zeroes
        while (index < n && (s.charAt(index) == ' ' || s.charAt(index) == '0')) {
            index++;
        }
        int sign = 1; 
        if (index < n && !Character.isDigit(s.charAt(index))) {
            sign = s.charAt(index) == '-' ? -1 : 1;
            index++;
        }
        long result = 0L;
        while (index < n && Character.isDigit(s.charAt(index))) { // TC: O(n)
            result = result * 10 + (s.charAt(index) - '0');
            index++;
        }
        if (result > Integer.MAX_VALUE) {
            if (sign == -1) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        return sign * (int) result; 
    }
}
