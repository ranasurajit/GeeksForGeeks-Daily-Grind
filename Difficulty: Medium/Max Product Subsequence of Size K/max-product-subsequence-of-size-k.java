class Solution {
    public int maxProduct(int[] arr, int k) {
        int n = arr.length;

        // maxDP[j] = maximum product possible by selecting j elements
        // minDP[j] = minimum product possible by selecting j elements
        long[] maxDP = new long[k + 1];
        long[] minDP = new long[k + 1];

        // Initialize
        for (int j = 0; j <= k; j++) {
            maxDP[j] = Long.MIN_VALUE;
            minDP[j] = Long.MAX_VALUE;
        }

        maxDP[0] = 1;
        minDP[0] = 1;

        for (int num : arr) {
            // Traverse backwards so that each element is used only once
            for (int j = k; j >= 1; j--) {

                if (maxDP[j - 1] == Long.MIN_VALUE)
                    continue;

                long product1 = maxDP[j - 1] * num;
                long product2 = minDP[j - 1] * num;

                long newMax = Math.max(product1, product2);
                long newMin = Math.min(product1, product2);

                maxDP[j] = Math.max(maxDP[j], newMax);
                minDP[j] = Math.min(minDP[j], newMin);
            }
        }

        return (int) maxDP[k];
    }
}
