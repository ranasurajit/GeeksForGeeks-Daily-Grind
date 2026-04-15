class Solution {
    /**
     * Approach : Using String Simulation Approach
     * 
     * TC: O(n1 + n2 + n3) ~ O(n)
     * SC: O(1)
     */
    public int myAtoi(String s) {
        int n = s.length();
        int index = 0;
        // clean-up all leading spaces
        while (index < n && s.charAt(index) == ' ') { // TC: O(n1)
            index++;
        }
        int sign = 1;
        // retrieve the sign
        if (index < n && !Character.isDigit(s.charAt(index))) { // TC: O(n2)
            sign = s.charAt(index) == '-' ? -1 : 1;
            index++;
        }
        long result = 0L;
        while (index < n && Character.isDigit(s.charAt(index))) { // TC: O(n3)
            int digit = s.charAt(index) - '0';
            // early pruning if number overflows 32-bit signed integer
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + digit;
            index++;
        }
        return sign * (int) result; 
    }
}
