class Solution {
    /**
     * Approach : Using Math + Simulation Approach
     * 
     * TC : O(m + n) + O(log(Min(a, b))) ~ O(m + n)
     * SC : O(log(Min(a, b)))
     */
    public int minSteps(int m, int n, int d) {
        if (d == 0) {
            // no steps needed
            return 0;
        }
        if (d == m || d == n) {
            // need to fill only 1 jug
            return 1;
        }
        /**
         * Intuition is d is always a value
         * which is multiple of gcd(m, n)
         */
        if (d > Math.max(m, n)) {
            // not possible to obtain d in any number of operations
            return -1;
        }
        if (d % gcd(m, n) != 0) { // TC: O(log(Min(a, b))), SC: O(log(Min(a, b)))
            return -1;
        }
        int steps1 = pourSteps(m, n, d); // TC : O(m + n), SC : O(1)
        int steps2 = pourSteps(n, m, d); // TC : O(m + n), SC : O(1)
        return Math.min(steps1, steps2);
    }
    
    /**
     * Using Math + Simulation Approach
     * 
     * TC : O(m + n)
     * SC: O(1)
     */
    private int pourSteps(int fromCap, int toCap, int d) {
        int from = fromCap; // filled to full
        int to = 0;
        int steps = 1; // as in Step1 from is filled to fromCap
        while (from != d && to != d) {
            int temp = Math.min(from, toCap - to);
            to += temp;
            from -= temp;
            steps++;
            if (from == d || to == d) {
                break;
            }
            if (from == 0) {
                from = fromCap; // fill from container
                steps++;
            }
            if (to == toCap) {
                to = 0; // empty to container
                steps++;
            }
        }
        return steps;
    }

    /**
     * Using Math Approach
     * 
     * TC : O(log(Min(a, b)))
     * SC : O(log(Min(a, b)))
     */
    private int gcd (int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
