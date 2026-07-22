class Solution {
    /**
     * Approach : Using Simulation + Binary Search Approach
     * 
     * TC : O(n x log(k))
     * SC : O(k)
     * 
     * where k = length of Longest Increasing Subsequence
     */
    public int minDeletions(int[] arr) {
        int n = arr.length;
        List<Integer> list = new ArrayList<>(); // SC : O(k)
        for (int i = 0; i < n; i++) { // TC : O(n)
            int current = arr[i];
            if (list.isEmpty() || current > list.get(list.size() - 1)) {
                list.add(current);
            } else {
                int idx = findLowerBound(list, current); // TC : O(log(k))
                list.set(idx, current);
            }
        }
        return n - list.size();
    }

    /**
     * Using Binary Search (Lower Bound) Approach
     * 
     * TC : O(log(k))
     * SC : O(1)
     */
    private int findLowerBound(List<Integer> list, int x) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) { // TC : O(log(k))
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
