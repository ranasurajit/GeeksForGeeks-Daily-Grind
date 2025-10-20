class Solution {
    /**
     * Approach : Using Math Approach
     * 
     * TC: O(N) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(2 x N) ~ O(N)
     */
    public ArrayList<Integer> countBSTs(int[] arr) {
        int n = arr.length;
        int[][] sorted = new int[n][2]; // SC: O(2 x N) ~ O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            sorted[i][0] = arr[i];
            sorted[i][1] = i;
        }
        // sorting the 'sorted' array based on its 0th index of every element
        Arrays.sort(sorted, (a, b) -> a[0] - b[0]); // TC: O(N x log(N))
        ArrayList<Integer> fact = computeFact(2 * n);
        /**
         * calculate number of BSTs based upon catalan number calculation
         * numBSTs = Catalan(left) * Catalan(right)
         */
        ArrayList<Integer> numBSTs = new ArrayList<Integer>(Collections.nCopies(n, 0));
        for (int i = 0; i < n; i++) { // TC: O(N)
            int originalIndex = sorted[i][1];
            numBSTs.set(originalIndex, catalan(i, fact) * catalan(n - i - 1, fact));
        }
        return numBSTs;
    }

    /**
     * Using Math Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private int catalan(int n, ArrayList<Integer> fact) {
        return fact.get(2 * n) / (fact.get(n) * fact.get(n + 1));
    }
    
    /**
     * Using Math Approach
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     */
    private ArrayList<Integer> computeFact(int num) {
        ArrayList<Integer> fact = new ArrayList<Integer>(); // SC: O(N)
        fact.add(1);
        for (int i = 1; i <= num; i++) { // TC: O(2 x N)
            fact.add(fact.get(i - 1) * i);
        }
        return fact;
    }
}
