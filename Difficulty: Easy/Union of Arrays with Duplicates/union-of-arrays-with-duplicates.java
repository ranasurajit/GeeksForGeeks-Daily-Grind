class Solution {
    /**
     * Approach : Using Hashing Approach
     * 
     * TC: O(M) + O(N) ~ O(M + N)
     * SC: O(M + N)
     */
    public static ArrayList<Integer> findUnion(int[] a, int[] b) {
        Set<Integer> set = new HashSet<Integer>(); // SC: O(M + N)
        for (int x : a) { // TC: O(M)
            set.add(x);
        }
        for (int x : b) { // TC: O(N)
            set.add(x);
        }
        return new ArrayList<Integer>(set);
    }
}
