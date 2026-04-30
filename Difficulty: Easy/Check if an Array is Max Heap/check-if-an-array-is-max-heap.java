class Solution {
    /**
     * Approach : Using Simulation Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    public boolean isMaxHeap(int[] arr) {
        int n = arr.length;
        int index = 0;
        while (index < n) { // TC : O(n)
            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;
            if (leftIndex < n && arr[index] < arr[leftIndex]) {
                return false;
            }
            if (rightIndex < n && arr[index] < arr[rightIndex]) {
                return false;
            }
            index++;
        }
        return true;
    }
}
