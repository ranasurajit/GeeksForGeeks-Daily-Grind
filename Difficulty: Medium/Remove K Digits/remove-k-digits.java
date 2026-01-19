class Solution {
    /**
     * Approach II : Using Monotonic Deque Approach
     * 
     * TC: O(N) + O(N - K) ~ O(N)
     * SC: O(N) + O(N - K) ~ O(N)
     */
    public String removeKdig(String s, int k) {
        int n = s.length();
        if (k == n) {
            return "0";
        }
        // we will store the characters in the Deque
        Deque<Character> deque = new ArrayDeque<Character>(); // SC: O(N)
        int i = 0;
        while (i < n) { // TC: O(N)
            char ch = s.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peek() > ch) {
                deque.pop();
                k--;
            }
            deque.push(ch);
            i++;
        }
        while (!deque.isEmpty() && k > 0) {
            deque.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder(); // SC: O(N - K)
        while (!deque.isEmpty()) { // TC: O(N - K)
            sb.append(deque.pop());
        }
        sb = sb.reverse();
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            // only remove leading zeros if length > 1
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * Approach I : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N - K) ~ O(N)
     * SC: O(N) + O(N - K) ~ O(N)
     */
    public String removeKdigUsingStack(String s, int k) {
        int n = s.length();
        if (k == n) {
            return "0";
        }
        // we will store the characters in the Stack
        Stack<Character> st = new Stack<Character>(); // SC: O(N)
        int i = 0;
        while (i < n) { // TC: O(N)
            char ch = s.charAt(i);
            while (!st.isEmpty() && k > 0 && st.peek() > ch) {
                st.pop();
                k--;
            }
            st.push(ch);
            i++;
        }
        while (!st.isEmpty() && k > 0) {
            st.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder(); // SC: O(N - K)
        while (!st.isEmpty()) { // TC: O(N - K)
            sb.append(st.pop());
        }
        sb = sb.reverse();
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            // only remove leading zeros if length > 1
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
