class Solution {
    /**
     * Approach : Using Array Prefix-Sum Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     */
    public ArrayList<Integer> optimalArray(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>();
        /**
         * we need to compute prefix sum of elements
         */
        int[] prefixSum = new int[n]; // SC : O(n)
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        for (int i = 0; i < n; i++) { // TC : O(n)
            // for a sorted array median is the mid index
            int mid = i / 2;
            int median = arr[mid];
            int count = mid + 1;
            // left operations - expected sum - actual sum till mid
            int leftOperations = (count * median) - prefixSum[mid];
            // right operations - actual sum till (i - mid) - expected sum till mid
            int rightOperations = (prefixSum[i] - prefixSum[mid]) -
                (i - mid) * median;
            result.add(leftOperations + rightOperations);
        }
        return result;
    }
}
