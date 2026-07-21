class Solution {
    /**
     * Approach : Using Hashing Approach
     * 
     * TC : O(n1 + n2) ~ O(n)
     * SC : O(26) ~ O(1)
     */
    public int maxIndexDifference(String s) {
        int n = s.length();
        int start = -1;
        Set<Integer> set = new HashSet<>(); // SC : O(26)
        for (int i = 0; i < n; i++) { // TC : O(n1)
            if (s.charAt(i) == 'a') {
                start = i;
                set.add(0);
                break;
            }
        }
        if (start == -1) {
            // not possible to choose a start index
            return -1;
        }
        int maxDiff = 0;
        for (int i = start + 1; i < n; i++) { // TC : O(n2)
            int chIdx = s.charAt(i) - 'a';
            int prevIdx = chIdx - 1;
            if (set.contains(prevIdx)) {
                set.add(chIdx);
                maxDiff = Math.max(maxDiff, i - start);
            }
        }
        return maxDiff;
    }
}
