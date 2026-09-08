class Solution {
    /**
     * Approach : Using Math Approach
     * 
     * TC : O(log10(n))
     * SC : O(1)
     */
    public int findMax(int n) {
        int maxSum = sumDigits(n); // TC : O(log10(n))
        int pow = 1;
        int result = n;
        while (pow <= n) {
            int candidate = (((n / pow) - 1) * pow) + (pow - 1);
            if (candidate > 0) {
                int candidateSum = sumDigits(candidate); // TC : O(log10(n))
                if (candidateSum > maxSum || (candidateSum == maxSum && 
                    candidate > result)) {
                    result = candidate;
                    maxSum = candidateSum;
                }
            }
            if (pow > n / 10) {
                // avoid int overflow
                break;
            }
            pow = pow * 10;
        }
        return result;
    }
    
    /**
     * Using Math Approach
     * 
     * TC : O(log10(n))
     * SC : O(1)
     */
    private int sumDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += (num % 10);
            num = num / 10;
        }
        return sum;
    }
}
