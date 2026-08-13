class Solution {
    /**
     * Approach : Using Simulation Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     */
    public boolean isPossible(int[] arr, int s, int x) {
        if (s == x) {
            return true;
        }
        int n = arr.length;
        long total = (long) s;
        ArrayList<Long> paper = new ArrayList<>(); // SC : O(n)
        paper.add((long) s);
        for (int i = 0; i < n; i++) { // TC : O(n)
            long next = total + arr[i];
            if (next > x) {
                /**
                 * total will continue to increase so, no 
                 * point of checking anything further
                 */
                break;
            }
            paper.add(next);
            total += next;
        }
        long remaining = (long) x;
        for (int i = paper.size() - 1; i >= 0; i--) { // TC : O(n)
            if (remaining >= paper.get(i)) {
                remaining -= paper.get(i);
            }
            if (remaining == 0) {
                return true;
            }
        }
        return false;
    }
}
