// User function Template for Java

class Solution {
    /**
     * Approach : Using Math + Two Pointers Approach
     * 
     * TC : O(log(N))
     * SC : O(log(N))
     */
    static long isPallindrome(long N) {
        StringBuilder sb = new StringBuilder(); // SC : O(log(N))
        while (N > 0) { // TC : O(log(N))
            sb.append((N & 1));
            N >>= 1;
        }
        int p = 0;
        int q = sb.length() - 1;
        while (p < q) { // TC : O(log(N))
            if (sb.charAt(p) != sb.charAt(q)) {
                return 0L;
            }
            p++;
            q--;
        }
        return 1L;
    }
}
