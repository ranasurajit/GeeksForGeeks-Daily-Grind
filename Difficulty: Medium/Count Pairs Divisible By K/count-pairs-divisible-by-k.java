class Solution {
    /**
     * Approach : Using Hashing Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public int countKdivPairs(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>(); // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            int rem = arr[i] % k;
            if (rem != 0) {
                count += map.getOrDefault(k - rem, 0);
            } else {
                count += map.getOrDefault(0, 0);
            }
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }
        return count;
    }
}
