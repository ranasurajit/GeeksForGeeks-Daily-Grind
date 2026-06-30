class Solution {
    private int n;
    private int m;

    /**
     * Approach III : Using Optimal (Hashing + LIS + Binary Search) Approach
     * 
     * TC : O(m) + O(n) + O(n x log(k)) ~ O(m + n x log(k))
     * SC : O(m) + O(n) + O(n) ~ O(m + n)
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int minInsAndDel(int[] a, int[] b) {
        this.n = a.length;
        this.m = b.length;
        /**
         * The memoization will cause TLE for the given constraints
         * also we need to use the property of array 'b' which is 
         * sorted and all the elements in it are distinct
         * 
         * so we can use a HashMap to store { element, index } to 
         * point out the indices that may be needed to modify i.e.
         * delete or insert
         */
        Map<Integer, Integer> bMap = new HashMap<>(); // SC : O(m)
        for (int i = 0; i < m; i++) { // TC : O(m)
            bMap.put(b[i], i);
        }
        ArrayList<Integer> commonList = new ArrayList<>(); // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (bMap.containsKey(a[i])) {
                commonList.add(a[i]);
            }
        }
        /**
         * now since array 'a' is sorted so in 'commonList' we
         * need to find the maximum length of LIS
         */
        int size = commonList.size();
        ArrayList<Integer> lis = new ArrayList<>(); // SC : O(n)
        lis.add(commonList.get(0));
        for (int i = 1; i < size; i++) { // TC : O(n)
            int current = commonList.get(i);
            if (current > lis.get(lis.size() - 1)) {
                lis.add(current);
            } else {
                int idx = lowerBound(lis, current); // TC : O(log(k))
                if (idx > -1) {
                    lis.set(idx, current);
                }
            }
        }
        int p = lis.size();
        /**
         * now we can have at-most 'p' elements retained in array 'a'
         * while other (n - p) elements which needs to be deleted and
         * other (m - p) elements that needs to be inserted
         */
        return (n - p) + (m - p);
    }
    
    /**
     * Using Binary Search (Lower Bound) Approach
     * 
     * TC : O(log(k))
     * SC : O(1)
     */
    private int lowerBound(ArrayList<Integer> lis, int target) {
        int low = 0;
        int high = lis.size() - 1;
        while (low <= high) { // TC : O(log(k))
            int mid = low + (high - low) / 2;
            if (target <= lis.get(mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     * 
     * TC : O(m x n)
     * SC : O(m x n) + O(m + n)
     *  - O(m x n) - memoization memory
     *  - O(m + n) - recursion stack
     * 
     * Time Limit Exceeded (1110 / 1111 testcases passed)
     */
    public int minInsAndDelMemoization(int[] a, int[] b) {
        this.n = a.length;
        this.m = b.length;
        int[][] memo = new int[n][m]; // SC : O(n x m)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, 0, a, b, memo);
    }
    
    /**
     * Using Memoization Approach
     * 
     * TC : O(m x n)
     * SC : O(m + n)
     */
    private int solveMemoization(int i, int j, int[] a, int[] b, int[][] memo) {
        // Base Case
        if (i == n) {
            // we need to insert (m - j) elements
            return m - j;
        }
        if (j == m) {
            // we need to delete (n - i) elements
            return n - i;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursion Calls
        int noOperation = Integer.MAX_VALUE;
        int deleteOperation = Integer.MAX_VALUE;
        int insertOperation = Integer.MAX_VALUE;
        if (a[i] == b[j]) {
            noOperation = solveMemoization(i + 1, j + 1, a, b, memo);
        } else {
            deleteOperation = 1 + solveMemoization(i + 1, j, a, b, memo);
            insertOperation = 1 + solveMemoization(i, j + 1, a, b, memo);
        }
        return memo[i][j] = 
            Math.min(noOperation, Math.min(deleteOperation, insertOperation));
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC : O(2 ^ (m + n))
     * SC : O(m + n)
     * - O(m + n) - recursion stack
     * 
     * Time Limit Exceeded (11 / 1111 testcases passed)
     */
    public int minInsAndDelRecursion(int[] a, int[] b) {
        this.n = a.length;
        this.m = b.length;
        return solveRecursion(0, 0, a, b);
    }
    
    /**
     * Using Recursion Approach
     * 
     * TC : O(2 ^ (m + n))
     * SC : O(m + n)
     */
    private int solveRecursion(int i, int j, int[] a, int[] b) {
        // Base Case
        if (i == n) {
            // we need to insert (m - j) elements
            return m - j;
        }
        if (j == m) {
            // we need to delete (n - i) elements
            return n - i;
        }
        // Recursion Calls
        int noOperation = Integer.MAX_VALUE;
        int deleteOperation = Integer.MAX_VALUE;
        int insertOperation = Integer.MAX_VALUE;
        if (a[i] == b[j]) {
            noOperation = solveRecursion(i + 1, j + 1, a, b);
        } else {
            deleteOperation = 1 + solveRecursion(i + 1, j, a, b);
            insertOperation = 1 + solveRecursion(i, j + 1, a, b);
        }
        return Math.min(noOperation, Math.min(deleteOperation, insertOperation));
    }
}
