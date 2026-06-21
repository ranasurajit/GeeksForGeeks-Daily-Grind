class Solution {
    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    public int maxArea(List<Integer> height) {
        int n = height.size();
        int p = 0;
        int q = n - 1;
        /**
         * we will shrink the left side first and then in
         * another loop we will shrink the right side to
         * capture the maximum area between bars
         */
        int maxArea = 0;
        while (q - p > 1) { // TC : O(n)
            int heightLeft = height.get(p);
            int heightRight = height.get(q);
            int base = (q - p - 1);
            int currentArea = base * Math.min(heightLeft, heightRight);
            maxArea = Math.max(maxArea, currentArea);
            if (heightLeft < heightRight) {
                // we can try for next incremented left index
                p++;
            } else {
                q--;
            }
        }
        return maxArea;
    }
}
