class Solution {
    /**
     * Approach : Using Array Pre-Processing Approach
     * 
     * TC: O(n) + O(n) + O(n) ~ O(n)
     * SC: O(n) + O(n) ~ O(n)
     */
    public ArrayList<Integer> find3Numbers(int[] arr) {
        int n = arr.length;
        int[] prefixMin = new int[n]; // SC: O(n)
        int[] suffixMax = new int[n]; // SC: O(n)
        prefixMin[0] = arr[0];
        for (int i = 1; i < n; i++) { // TC: O(n)
            prefixMin[i] = Math.min(prefixMin[i - 1], arr[i]);
        }
        suffixMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) { // TC: O(n)
            suffixMax[i] = Math.max(suffixMax[i + 1], arr[i]);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) { // TC: O(n)
            if (arr[i] > prefixMin[i - 1] && arr[i] < suffixMax[i + 1]) {
                result.add(prefixMin[i - 1]);
                result.add(arr[i]);
                result.add(suffixMax[i + 1]);
                return result;
            }
        }
        return result;
    }
}
