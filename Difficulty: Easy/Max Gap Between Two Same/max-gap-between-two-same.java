class Solution {
    /**
     * Approach : Using String Simulation Approach
     * 
     * TC : O(n)
     * SC : O(26) ~ O(1)
     */
    public int maxCharGap(String s) {
        int n = s.length();
        int[] charsIndex = new int[26]; // SC : O(26)
        Arrays.fill(charsIndex, -1);
        int maxGap = -1;
        for (int i = 0; i < n; i++) { // TC : O(n)
            int idx = s.charAt(i) - 'a';
            if (charsIndex[idx] == -1) {
                charsIndex[idx] = i;
            } else {
                maxGap = Math.max(maxGap, i - charsIndex[idx] - 1);
            }
        }
        return maxGap;
    }
};
