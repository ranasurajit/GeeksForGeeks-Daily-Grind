class Solution {
    /**
     * Approach II : Using Optimal (XOR Operation) Approach
     * 
     * TC : O(q) + O(n) + O(n x log(n)) ~ O(q + n + n x log(n))
     * SC : O(1)
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public ArrayList<Integer> constructList(int[][] queries) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        int q = queries.length;
        int effXOR = 0;
        for (int i = 0; i < q; i++) { // TC : O(q)
            int[] query = queries[i];
            int type = query[0];
            int x = query[1];
            if (type == 0) {
                result.add(x ^ effXOR); // to negate future application of effXOR
            } else {
                effXOR ^= x;
            }
        }
        for (int i = 0; i < result.size(); i++) { // TC : O(n)
            result.set(i, (result.get(i) ^ effXOR));
        }
        Collections.sort(result); // TC : O(n x log(n))
        return result;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     * 
     * TC : O(q x n) + O(n x log(n)) ~ O(q x n)
     * SC : O(1)
     * 
     * Time Limit Exceeded (1110 / 1111 testcases passed)
     */
    public ArrayList<Integer> constructListBruteForce(int[][] queries) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        int q = queries.length;
        for (int i = 0; i < q; i++) { // TC : O(q)
            int[] query = queries[i];
            int type = query[0];
            int x = query[1];
            if (type == 0) {
                result.add(x);
            } else {
                for (int j = 0; j < result.size(); j++) { // TC : O(n)
                    result.set(j, (result.get(j) ^ x));
                }
            }
        }
        Collections.sort(result); // TC : O(n x log(n))
        return result;
    }
}
