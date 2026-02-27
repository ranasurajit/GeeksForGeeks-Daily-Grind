class Solution {
    /**
     * Approach : Using Simulation Approach
     * 
     * TC: O(Min(N, M))
     * SC: O(Min(N, M))
     */
    public static ArrayList<Integer> findClosestPair(int arr1[], int arr2[], int x) {
        int n = arr1.length;
        int m = arr2.length;
        int p = 0;     // pointer at the start of the arr1
        int q = m - 1; // pointer at the end of the arr2
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<int[]> pairs = new ArrayList<>(); // SC: O(Min(N, M))
        while (p < n && q >= 0) { // TC: O(Min(N, M))
            if (arr1[p] + arr2[q] == x) {
                result.add(arr1[p]);
                result.add(arr2[q]);
                return result;
            } else if (arr1[p] + arr2[q] < x) {
                // increment p
                pairs.add(new int[] { arr1[p], arr2[q] });
                p++;
            } else {
                // decrement p
                pairs.add(new int[] { arr1[p], arr2[q] });
                q--;
            }
        }
        int minDiff = Integer.MAX_VALUE;
        int[] minPair = new int[2]; // SC: O(2) ~ O(1)
        for (int[] pair : pairs) {
            int sum = pair[0] + pair[1];
            if (minDiff > Math.abs(x - sum)) {
                minDiff = Math.abs(x - sum);
                minPair = new int[] { pair[0], pair[1] };
            }
        }
        result.add(minPair[0]);
        result.add(minPair[1]);
        return result;
    }
}
