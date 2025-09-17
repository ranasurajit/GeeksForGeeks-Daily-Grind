class Solution {
    /**
     * Approach : Using Stack + String Simulation Approach
     * 
     * TC: O(N + L) - ammortized
     * SC: O(N) + O(L) ~ O(N + L) - ammortized
     * 
     * where L = final length of result
     */
    static String decodeString(String s) {
        int n = s.length();
        Stack<String> stringStack = new Stack<String>();  // SC: O(N)
        Stack<Integer> countStack = new Stack<Integer>(); // SC: O(N)
        StringBuilder result = new StringBuilder();       // SC: O(N)
        int k = 0;
        for (char ch : s.toCharArray()) { // TC: O(N)
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                countStack.push(k);
                stringStack.push(result.toString());
                k = 0; // reset k after pushing it to 'countStack'
                result = new StringBuilder(); // reset 'result' after pushing it to 'stringStack'
            } else if (ch == ']') {
                // we need to wrap up calculation to append String k times
                int repeat = countStack.pop();
                StringBuilder segment = new StringBuilder(stringStack.pop()); // SC: O(L)
                for (int i = 0; i < repeat; i++) { // TC: O(L)
                    segment.append(result.toString());
                }
                result = segment;
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}
