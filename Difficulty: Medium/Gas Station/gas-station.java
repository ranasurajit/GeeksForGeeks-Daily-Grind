class Solution {
    /**
     * Approach : Using Greedy Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int startStation(int[] gas, int[] cost) {
        int n = gas.length;
        int totalFuel = 0; // overall gas balance - this is a global checks for a feasible route
        int fuel = 0; // current gas balance
        int startIdx = 0;
        int i = 0;
        while (i < n) { // TC: O(N)
            int diffToReach = gas[i] - cost[i];
            totalFuel += diffToReach;
            fuel += diffToReach;
            if (fuel < 0) {
                // cannot start from index 'i'
                startIdx = i + 1;
                fuel = 0; // reset the current gas balance
            }
            i++;
        }
        return totalFuel >= 0 ? startIdx : -1;
    }
}
