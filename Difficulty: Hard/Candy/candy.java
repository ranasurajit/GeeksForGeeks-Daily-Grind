class Solution {
    /**
     * Approach : Using Array-PreProcessing Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int minCandy(int arr[]) {
        int n = arr.length;
        int[] prefix = new int[n]; // SC: O(N)
        Arrays.fill(prefix, 1);
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (arr[i] > arr[i - 1]) {
                prefix[i] = 1 + prefix[i - 1];
            }
        }
        int[] suffix = new int[n]; // SC: O(N)
        Arrays.fill(suffix, 1);
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            if (arr[i] > arr[i + 1]) {
                suffix[i] = 1 + suffix[i + 1];
            }
        }
        int countMinCandies = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            countMinCandies += Math.max(prefix[i], suffix[i]);
        }
        return countMinCandies;
    }
}
