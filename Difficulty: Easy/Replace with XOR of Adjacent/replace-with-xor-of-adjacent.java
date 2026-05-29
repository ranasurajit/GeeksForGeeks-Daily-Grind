class Solution {
    /**
     * Approach : Using Array Simulation + Bit-Manipulation Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     */
    public void replaceElements(int[] arr) {
        int n = arr.length;
        int[] nums = new int[n]; // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (i == 0) {
                nums[i] = (arr[i] ^ arr[i + 1]);
            } else if (i == n - 1) {
                nums[i] = (arr[i] ^ arr[i - 1]);
            } else {
                nums[i] = (arr[i - 1] ^ arr[i + 1]);
            }
        }
        for (int i = 0; i < n; i++) { // TC : O(n)
            arr[i] = nums[i];
        }
    }
}
