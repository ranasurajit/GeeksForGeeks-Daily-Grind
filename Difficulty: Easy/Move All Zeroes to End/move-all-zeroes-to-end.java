class Solution {
    /**
     * Approach I : Using Array Simulation Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    void pushZerosToEnd(int[] arr) {
        int n = arr.length;
        List<Integer> result = new ArrayList<>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (arr[i] > 0) {
                result.add(arr[i]);
            }
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (i < result.size()) {
                arr[i] = result.get(i);
            } else {
                arr[i] = 0;
            }
        }
    }
}
