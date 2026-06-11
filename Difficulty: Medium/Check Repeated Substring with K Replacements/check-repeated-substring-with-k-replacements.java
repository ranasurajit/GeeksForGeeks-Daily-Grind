class Solution {
    /**
     * Approach : Using String Simulation + Hashing Approach
     * 
     * TC : O((n / k) x k) + O(n / k) ~ O(n)
     * SC : O(k) + O(n / k) ~ O(n)
     */
    public boolean kSubstr(String s, int k) {
        int n = s.length();
        // n should be divisible by k to perform replacements
        if (n % k != 0) {
            return false;
        }
        Map<String, Integer> map = new HashMap<>(); // SC : O(n / k)
        for (int i = 0; i < n; i += k) { // TC : O(n / k)
            StringBuilder sb = new StringBuilder(); // SC : O(k)
            for (int j = i; j < i + k; j++) { // TC : O(k)
                sb.append(s.charAt(j));
            }
            String key = sb.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        // replacements is possible if we have only 1 distinct k characters
        if (map.size() == 1) {
            // no replacements needed
            return true;
        }
        if (map.size() > 2) {
            // cannot make k characters same
            return false;
        }
        boolean isFreqOneFound = false;
        for (Integer val : map.values()) { // TC : O(n / k)
            if (val == 1) {
                isFreqOneFound = true;
                break;
            }
        }
        return isFreqOneFound;
    }
}
