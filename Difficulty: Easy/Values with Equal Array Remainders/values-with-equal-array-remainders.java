class Solution {
    /**
     * Approach : GCD of Differences + Count Divisors
     *
     * TC : O(n + √M)
     * SC : O(1)
     */
    public int sameMod(int[] arr) {
        int gcd = 0;
        // Calculate GCD of differences from arr[0]
        for (int i = 1; i < arr.length; i++) {
            int difference = Math.abs(arr[i] - arr[0]);
            gcd = findGCD(gcd, difference);
        }
        // All elements are equal
        if (gcd == 0) {
            return -1;
        }
        // Count positive divisors of gcd
        int count = 0;
        for (int i = 1; i * i <= gcd; i++) {
            if (gcd % i == 0) {
                count++;
                if (i != gcd / i) {
                    count++;
                }
            }
        }
        return count;
    }

    private int findGCD(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }
}
