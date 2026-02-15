// User function Template for Java

class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) + Sorting Approach
     * 
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int findMinDiff(ArrayList<Integer> arr, int m) {
        int n = arr.size();
        // since picking order of packets do not matter so we can sort ArrayList 'arr'
        Collections.sort(arr); // TC: O(N x log(N))
        /**
         * now we can slide the window with constant size m to 
         * find the minimum and maximum differences
         */
        int i = 0;
        int j = 0;
        int minDiff = Integer.MAX_VALUE;
        while (j < n) { // TC: O(N)
            if (j - i + 1 == m) {
                // arr.get(j) is maximum and arr.get(i) is minimum
                minDiff = Math.min(minDiff, arr.get(j) - arr.get(i));
                // now remove computation from index 'i' and slide it
                i++;
            }
            j++;
        }
        return minDiff;
    }
}
