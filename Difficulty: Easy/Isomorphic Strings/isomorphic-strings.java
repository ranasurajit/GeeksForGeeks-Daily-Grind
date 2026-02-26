class Solution {
    /**
     * Approach II: Using Array as Hashing Approach
     * 
     * TC: O(N)
     * SC: O(26) + O(26) ~ O(1)
     */
    public boolean areIsomorphic(String s1, String s2) {
        int n = s1.length();
        // Map size is 26 as s1 and s2 consists of only lowercase English letters
        int[] map = new int[26];    // SC: O(26)
        int[] revMap = new int[26]; // SC: O(26)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int s1key = s1.charAt(i) - 'a';
            int s2key = s2.charAt(i) - 'a';
            if (map[s1key] != revMap[s2key]) {
                return false;
            }
            map[s1key] = i + 1;
            revMap[s2key] = i + 1;
        }
        return true;
    }

    /**
     * Approach I : Using Hashing Approach
     * 
     * TC: O(N)
     * SC: O(26) + O(26) ~ O(1)
     */
    public boolean areIsomorphicHAshing(String s1, String s2) {
        int n = s1.length();
        // Map size is 26 as s1 and s2 consists of only lowercase English letters
        Map<Integer, Integer> map = new HashMap<>();    // SC: O(26)
        Map<Integer, Integer> revMap = new HashMap<>(); // SC: O(26)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int s1key = s1.charAt(i) - 'a';
            int s2key = s2.charAt(i) - 'a';
            if (!map.containsKey(s1key)) {
                map.put(s1key, s2key);
            } else {
                if (s2key != map.get(s1key)) {
                    return false;
                }
            }
            if (!revMap.containsKey(s2key)) {
                revMap.put(s2key, s1key);
            } else {
                if (s1key != revMap.get(s2key)) {
                    return false;
                }
            }
        }
        return true;
    }
}
