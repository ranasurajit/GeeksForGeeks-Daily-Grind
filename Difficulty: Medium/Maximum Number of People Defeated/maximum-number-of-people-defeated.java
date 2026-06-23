class Solution {
    /**
     * Approach : Using Simulation Approach
     * 
     * TC : O(p^(1/3))
     * SC : O(1)
     */
    int maxPeopleDefeated(int p) {
        int count = 0;
        int i = 1;
        while (p > 0) { // TC : O(p^(1/3))
            if (i * i <= p) {
                p -= (i * i);
            } else {
                break;
            }
            count++;
            i++;
        }
        return count;
    }
};
