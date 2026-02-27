class Solution {
    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC: O(N + M)
     * SC: O(1)
     */
    public static ArrayList<Integer> findClosestPair(int arr1[], int arr2[], int x) {
        int n = arr1.length;
        int m = arr2.length;
        int p = 0;     // pointer at the start of the arr1
        int q = m - 1; // pointer at the end of the arr2
        int pair1 = -1;
        int pair2 = -1;
        int minDiff = Integer.MAX_VALUE;
        ArrayList<Integer> result = new ArrayList<>();
        while (p < n && q >= 0) { // TC: O(N + M)
            int sum = arr1[p] + arr2[q];
            if (sum == x) {
                pair1 = arr1[p];
                pair2 = arr2[q];
                break;
            } else if (sum < x) {
                if (minDiff > Math.abs(sum - x)) {
                    minDiff = Math.abs(sum - x);
                    pair1 = arr1[p];
                    pair2 = arr2[q];
                }
                // increment p
                p++;
            } else {
                // decrement p
                if (minDiff > Math.abs(sum - x)) {
                    minDiff = Math.abs(sum - x);
                    pair1 = arr1[p];
                    pair2 = arr2[q];
                }
                q--;
            }
        }
        result.add(pair1);
        result.add(pair2);
        return result;
    }
}
