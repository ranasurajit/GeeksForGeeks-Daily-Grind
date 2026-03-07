class Solution {
    /**
     * Approach II : Using Hashing Approach
     * 
     * TC: O(N) + O(N²) ~ O(N²)
     * SC: O(N)
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    boolean pythagoreanTriplet(int[] arr) {
        int n = arr.length;
        Set<Integer> set = new HashSet<>(); // SC: O(N)
        for (int i = 0; i < n; i++) {       // TC: O(N)
            set.add(arr[i] * arr[i]);
        }
        for (int i = 0; i < n - 1; i++) {     // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                if (set.contains(arr[i] * arr[i] + arr[j] * arr[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Approach I : Using Sorting + Two Pointers Approach
     * 
     * TC: O(N x log(N)) + O(N²) ~ O(N²)
     * SC: O(1)
     * 
     * Time Limit Exceeded (1010 / 1115 testcases passed)
     */
    boolean pythagoreanTripletTwoPointersSorting(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr); // TC: O(N x log(N))
        for (int r = n - 1; r >= 2; r--) { // TC: O(N)
            int p = 0;
            int q = r - 1;
            while (p < q) { // TC: O(N)
                int sumSqr = arr[p] * arr[p] + arr[q] * arr[q];
                int result = arr[r] * arr[r];
                if (sumSqr == result) {
                    return true;
                } else if (sumSqr < result) {
                    p++;
                } else {
                    q--;
                }
            }
        }
        return false;
    }
}
