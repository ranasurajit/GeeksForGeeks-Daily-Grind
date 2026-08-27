class Solution {
    /**
     * Approach : Using Height + Sorting Approach
     *
     * For every row, height[j] stores the number of consecutive
     * 1s ending at the current row in column j.
     *
     * Since columns can be swapped arbitrarily, we can arrange
     * these column heights in any order.
     *
     * After sorting the heights, for every possible width,
     * the minimum height of the selected columns determines
     * the rectangle height.
     *
     * TC : O(n x m x log (m))
     * SC : O(m)
     */
    public int maxArea(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] height = new int[m];
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            // Build histogram heights for current row
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            // Make a copy because height[] must retain
            // the column-wise information for the next row.
            int[] sortedHeight = height.clone();
            Arrays.sort(sortedHeight);
            // Try every possible width
            for (int j = 0; j < m; j++) {
                int width = m - j;
                int minHeight = sortedHeight[j];
                int area = width * minHeight;
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}
