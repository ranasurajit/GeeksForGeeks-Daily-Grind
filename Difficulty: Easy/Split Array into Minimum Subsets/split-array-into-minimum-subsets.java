class Solution {
    /**
     * Approach : Using Sorting Approach
     * 
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(1)
     */
    int minSubsets(int arr[]) {
        int n = arr.length;
        Arrays.sort(arr); // TC : O(n x log(n))
        int count = 1;
        for (int i = 1; i < n; i++) { // TC : O(n)
            if (arr[i] == arr[i - 1] + 1) {
                continue;
            } else {
                count++;
            }
        }
        return count;
    }
}
