class Solution {
    /**
     * Approach : Using Hashing Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public ArrayList<Integer> findDuplicates(int[] arr) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>(); // SC: O(N)
        for (int num : arr) { // TC: O(N)
            if (!set.add(num)) {
                result.add(num);
            }
        }
        return result;
    }
}
