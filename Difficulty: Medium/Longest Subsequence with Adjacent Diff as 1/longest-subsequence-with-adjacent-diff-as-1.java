class Solution {
    public int longestSubseq(int[] arr) {
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int num : arr) {
            int prev = Math.max(
                dp.getOrDefault(num - 1, 0),
                dp.getOrDefault(num + 1, 0)
            );

            dp.put(num, prev + 1);

            ans = Math.max(ans, dp.get(num));
        }

        return ans;
    }
}
