class Solution {
    /**
     * Approach I : Using Hashing Approach
     * 
     * TC: O(m x log(n) + n)
     * SC: O(n) + O(n) ~ O(n)
     */
    ArrayList<Integer> intersection(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        if (m < n) {
            // we will always ensure m > n
            return intersection(b, a);
        }
        // we will be storing set with smallest sized array
        Set<Integer> set = new HashSet<>(); // SC: O(n)
        for (int x : b) { // TC: O(n)
            set.add(x);
        }
        TreeSet<Integer> res = new TreeSet<>(); // SC: O(n)
        for (int x : a) { // TC: O(m)
            if (set.contains(x)) {
                res.add(x); // TC: O(log(n))
            }
        }
        return new ArrayList<>(res);
    }
}
