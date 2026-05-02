class Solution {
    /**
     * Approach : Using Bit-Manipulation + Math Approach
     * 
     * TC : O(n x log₁₀(n)) + O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(n)
     */
    ArrayList<Integer> sortBySetBitCount(int[] arr) {
        int n = arr.length;
        int[][] arrBits = new int[n][3];       // SC : O(3 x n) ~ O(n)
        for (int i = 0; i < n; i++) {          // TC : O(n)
            arrBits[i][0] = arr[i];
            arrBits[i][1] = countBits(arr[i]); // TC : O(log₁₀(n))
            arrBits[i][2] = i;
        }
        Arrays.sort(arrBits, (a, b) -> {
            if (a[1] == b[1]) {
                return a[2] - b[2];
            }
            return b[1] - a[1];
        });                                    // TC : O(n x log(n))
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {          // TC : O(n)
            result.add(arrBits[i][0]);
        }
        return result;
    }
    
    /**
     * Using Bit-Manipulation + Math Approach
     * 
     * TC : O(log₁₀(n))
     * SC : O(1)
     */
    private int countBits(int num) {
        int count = 0;
        while (num > 0) { // TC : O(log₁₀(n))
            if ((num & 1) == 1) {
                count++;
            }
            num >>= 1;
        }
        return count;
    }
}
