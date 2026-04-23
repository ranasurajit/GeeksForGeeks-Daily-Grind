class Solution {
    /**
     * Approach : Using Array Simulation Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    public int visibleBuildings(int arr[]) {
        int n = arr.length;
        int count = 1; // left most building will see the sun
        int prevMax = arr[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            if (arr[i] >= prevMax) {
                count++;
            }
            prevMax = Math.max(prevMax, arr[i]);
        }
        return count;
    }
}
