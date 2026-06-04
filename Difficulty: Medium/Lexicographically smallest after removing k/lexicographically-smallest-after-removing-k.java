class Solution {
    /**
     * Approach : Using Stack Approach
     * 
     * TC : O(n) + O(n - k) + O(n - k) ~ O(n)
     * SC : O(n - k) + O(n) ~ O(n)
     */
    public String lexicographicallySmallest(String s, int k) {
        int n = s.length();
        /**
         * n is even so, we need to check if
         * n is a power of 2, so that is possible
         * if n has only 1 set bit
         */
        if (Integer.bitCount(n) == 1) {
            k = k / 2;
        } else {
            k = k * 2;
        }
        if (k >= n) {
            // we end up removing all characters
            return "-1";
        }
        Stack<Integer> st = new Stack<>(); // SC : O(n - k)
        for (int i = 0; i < n; i++) { // TC : O(n)
            while (!st.isEmpty() && st.peek() > (s.charAt(i) - 'a') && k > 0) {
                st.pop();
                k--; // removed
            }
            st.push(s.charAt(i) - 'a');
        }
        while (k > 0) {
            st.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder(); // SC : O(n)
        while (!st.isEmpty()) { // TC : O(n - k)
            sb.append((char) (st.pop() + 'a'));
        }
        return sb.reverse().toString(); // TC : O(n - k)
    }
}
