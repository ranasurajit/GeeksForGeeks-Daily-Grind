class Solution {
    /**
     * Approach : Using String Simulation Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public String largestSwap(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] rightMax = new int[n]; // SC: O(N)
        int maxIdx = n - 1;
        rightMax[n - 1] = maxIdx;
        // first we will find the index having maximum value from right to left
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            if (chars[i] > chars[maxIdx]) {
                maxIdx = i;
            }
            rightMax[i] = maxIdx;
        }
        // now we will traverse from left to right to swap with chars[maxidx]
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (chars[i] < chars[rightMax[i]]) {
                // swap
                char temp = chars[rightMax[i]];
                chars[rightMax[i]] = chars[i];
                chars[i] = temp;
                break;
            }
        }
        return String.valueOf(chars);
    }
}
