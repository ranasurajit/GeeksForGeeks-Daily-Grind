class Solution {
    /**
     * Approach : Using Greedy + Two Pointers Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    public boolean canSeatAllPeople(int k, int[] seats) {
        int n = seats.length;
        int count = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (seats[i] == 0) {
                boolean leftVoid = i == 0 || seats[i - 1] == 0;
                boolean rightVoid = i == (n - 1) || seats[i + 1] == 0;
                if (leftVoid && rightVoid) {
                    // can place at seats[i]
                    seats[i] = 1;
                    count++;
                    if (count == k) {
                        return true;
                    }
                }
            }
        }
        return count >= k;
    }
}
