class Solution {
    /**
     * Approach : Using Sorting + Array Simulation Approach
     * 
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public ArrayList<int[]> mergeOverlap(int[][] arr) {
        Arrays.sort(arr, (a, b) -> a[0] - b[0]); // TC: O(N x log(N))
        ArrayList<int[]> merged = new ArrayList<int[]>();
        int lastStart = -1;
        int lastEnd = -1;
        for (int[] time : arr) { // TC: O(N)
            int start = time[0];
            int end = time[1];
            if (lastEnd != -1 && lastEnd >= start) {
                int newStart = Math.min(lastStart, start);
                int newEnd = Math.max(lastEnd, end);
                merged.set(merged.size() - 1, new int[] { newStart, newEnd });
                lastStart = newStart;
                lastEnd = newEnd;
            } else {
                merged.add(time);
                lastStart = start;
                lastEnd = end;
            }
        }
        return merged;
    }
}
