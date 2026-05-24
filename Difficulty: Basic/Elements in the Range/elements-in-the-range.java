class Solution {
    /**
     * Approach : Using Hashing Approach
     * 
     * TC : O(n) + O(r) ~ O(n + r)
     * SC : O(n)
     */
    public boolean checkElements(int start, int end, int[] arr) {
        int n = arr.length;
        Set<Integer> set = new HashSet<>(); // SC : O(n)
        for (int x : arr) { // TC : O(n)
            set.add(x);
        }
        for (int i = start; i <= end; i++) { // TC : O(r)
            if (!set.contains(i)) {
                return false;
            }
        }
        return true;
    }
}
