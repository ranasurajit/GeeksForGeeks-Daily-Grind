class Solution {
    /**
     * Approach : Using Sorting + Two Pointers Approach
     * 
     * TC : O(n x log(n)) + O(n²) ~ O(n²) 
     * SC : O(1)
     */
    public int countTriplets(int[] arr, int l, int r) {
        int n = arr.length;
        Arrays.sort(arr); // TC : O(n x log(n))
        return countTripletsWithSumLessThanK(arr, n, r)
            - countTripletsWithSumLessThanK(arr, n, l - 1); // TC : O(n²)
    }

    /**
     * Using Two Pointers Approach
     * 
     * TC : O(n²)
     * SC : O(1)
     */
    private int countTripletsWithSumLessThanK(int[] arr, int n, int target) {
        int count = 0;
        int i = 0;
        while (i <= n - 3) { // TC : O(n)
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];
                if (sum <= target) {
                    count += (k - j);
                    j++;
                } else {
                    k--;
                }
            }
            i++;
        }
        return count;
    }
}
