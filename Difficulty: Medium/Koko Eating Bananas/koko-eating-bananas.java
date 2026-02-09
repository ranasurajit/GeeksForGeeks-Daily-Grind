class Solution {
    /**
     * Approach : Using Binary Search on Answers Approach
     * 
     * TC: O(N x log(R)) + O(N) ~ O(N x log(R))
     * SC: O(1)
     * 
     * where R = Max(arr)
     */
    public int kokoEat(int[] arr, int k) {
        int n = arr.length;
        long low = 1L;
        long high = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            high = Math.max(high, arr[i]);
        }
        while (low <= high) { // TC: O(log(R))
            long mid = low + (high - low) / 2;
            if (timeTakenToFinishAllPiles(arr, n, mid) > k) { // TC: O(N)
                // Koko needs to increase the eating speed (bananas / hour)
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) low;
    }

    /**
     * Using Array Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private long timeTakenToFinishAllPiles(int[] arr, int n, long speed) {
        long time = 0;
        int i = 0;
        while (i < n) { // TC: O(N)
            time += (arr[i] / speed) + (arr[i] % speed == 0 ? 0 : 1);
            i++;
        }
        return time;
    }
}
