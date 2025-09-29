class Solution {
    /**
     * Approach : Prefix Sum + Sliding Window Min (TreeSet)
     * 
     * TC: O(N x log(N))
     * SC: O(N)
     */
    public int maxSubarrSum(int[] arr, int a, int b) {
        int n = arr.length;
        long[] prefix = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        long result = Long.MIN_VALUE;
        TreeSet<Pair> set = new TreeSet<>((p1, p2) -> 
            p1.value == p2.value ? p1.index - p2.index : Long.compare(p1.value, p2.value));

        for (int j = a; j <= n; j++) {
            // add prefix[j - a] into set (start of valid window)
            set.add(new Pair(prefix[j - a], j - a));

            // remove prefix[j - b - 1] if it goes out of window
            if (j - b - 1 >= 0) {
                set.remove(new Pair(prefix[j - b - 1], j - b - 1));
            }

            // min prefix in current window
            long minPrefix = set.first().value;
            result = Math.max(result, prefix[j] - minPrefix);
        }

        return (int) result;
    }
    
    static class Pair {
        long value;
        int index;
        Pair(long v, int i) { value = v; index = i; }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair other = (Pair) o;
            return this.value == other.value && this.index == other.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, index);
        }
    }
}
