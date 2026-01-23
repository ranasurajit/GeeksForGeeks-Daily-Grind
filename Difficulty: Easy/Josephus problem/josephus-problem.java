class Solution {
    /**
     * Approach : Using Recursion Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int josephus(int n, int k) {
        List<Integer> peopleList = new ArrayList<Integer>(); // SC: O(N)
        for (int i = 1; i <= n; i++) { // TC: O(N)
            peopleList.add(i);
        }
        k = k - 1; // converting k to 0 based counting
        return solve(0, n, k, peopleList); // TC: O(N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int solve(int start, int n, int k, List<Integer> peopleList) {
        // Base Case
        if (n == 1) {
            // in this case only 1 person is left
            return peopleList.get(0); 
        }
        // Recursion Calls
        // since 'peopleList' is a circular array
        int removedIdx = (start + k) % n; // kth person to be killed
        peopleList.remove(removedIdx);
        n--;
        return solve(removedIdx % n, n, k, peopleList);
    }
}
