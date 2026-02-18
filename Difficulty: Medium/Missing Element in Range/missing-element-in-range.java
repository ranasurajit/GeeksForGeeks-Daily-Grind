class Solution {
    /**
     * Approach : Using Hashing Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public ArrayList<Integer> missingRange(int[] arr, int low, int high) {
        Set<Integer> hs = new HashSet<Integer>(); // SC: O(N)
        for (int x : arr) { // TC: O(N)
            hs.add(x);
        }
        ArrayList<Integer> missing = new ArrayList<Integer>();
        for (int i = low; i <= high; i++) { // TC: O(N)
            if (!hs.contains(i)) {
                missing.add(i);
            }
        }
        return missing;
    }
}
