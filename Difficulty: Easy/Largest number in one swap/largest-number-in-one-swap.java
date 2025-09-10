class Solution {
    /**
     * Approach : Using Greedy Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public String largestSwap(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int[] rightMax = new int[n]; // SC: O(N)
        int maxIdx = n - 1;
        rightMax[n - 1] = maxIdx;
        // track max index from right to left
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            if (arr[i] > arr[maxIdx]) {
                maxIdx = i;
            }
            rightMax[i] = maxIdx;
        }
        // now we will check for any index from left to right which is less than maxIdx
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (arr[i] < arr[rightMax[i]]) {
                return swapAndReturn(s, i, rightMax[i]);
            }
        }
        return s;
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(1)
     * SC: O(N)
     */
    private String swapAndReturn(String s, int a, int b) {
        char[] chars = s.toCharArray(); // SC: O(N)
        char temp = chars[b];
        chars[b] = chars[a];
        chars[a] = temp;
        return String.valueOf(chars);
    }
}
