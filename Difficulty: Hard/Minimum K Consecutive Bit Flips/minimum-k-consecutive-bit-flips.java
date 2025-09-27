class Solution {
    public int kBitFlips(int[] arr, int k) {
        int n = arr.length;

        int flips = 0;
        boolean[] isFlipped = new boolean[n];
        int flipCountFromPastForCurri = 0;

        for (int i = 0; i < n; i++) {
            if (i >= k && isFlipped[i - k]) {
                flipCountFromPastForCurri--;
            }

            if (flipCountFromPastForCurri % 2 == arr[i]) {
                if (i + k > n) {
                    return -1;
                }
                flipCountFromPastForCurri++;
                isFlipped[i] = true;
                flips++;
            }
        }

        return flips;
    }
}
