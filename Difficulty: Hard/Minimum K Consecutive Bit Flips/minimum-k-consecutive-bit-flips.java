class Solution {
    /**
     * Approach IV : Using Optimal (Two Pointers + Deque) Approach
     * 
     * TC: O(N)
     * SC: O(K)
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public int kBitFlips(int[] arr, int k) {
        int n = arr.length;
        int operations = 0;
        int flipsFromPastAtIndex = 0;
        Deque<Integer> flipDeque = new ArrayDeque<>(); // SC: O(K)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (i >= k) {
                // we have an extra flip from past so reduce it
                flipsFromPastAtIndex -= flipDeque.pollFirst();
            }
            if (flipsFromPastAtIndex % 2 == arr[i]) {
                /**
                 * we need a flip at index 'i'
                 * - if arr[i] is 1 then odd flips will make it 0 
                 * - if arr[i] is 0 then even flips will make it 0
                 */
                if (i + k > n) {
                    // not possible to flip
                    return -1;
                }
                flipsFromPastAtIndex++;
                operations ++;
                flipDeque.offerLast(1); // flipped
            } else {
                flipDeque.offerLast(0); // not flipped
            }
        }
        return operations;
    }

    /**
     * Approach III : Using Optimal (Two Pointers) without Extra Space Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public int kBitFlipsOptimalII(int[] arr, int k) {
        int n = arr.length;
        int operations = 0;
        int flipsFromPastAtIndex = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (i >= k && arr[i - k] == 2) {
                // we have an extra flip from past so reduce it
                flipsFromPastAtIndex--;
            }
            if (flipsFromPastAtIndex % 2 == arr[i]) {
                /**
                 * we need a flip at index 'i'
                 * - if arr[i] is 1 then odd flips will make it 0 
                 * - if arr[i] is 0 then even flips will make it 0
                 */
                if (i + k > n) {
                    // not possible to flip
                    return -1;
                }
                arr[i] = 2; // marking flipped
                flipsFromPastAtIndex++;
                operations ++;
            }
        }
        return operations;
    }

    /**
     * Approach II : Using Optimal (Two Pointers) with Extra Space Approach
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * Accepted (1115 / 1115 testcases passed)
     */
    public int kBitFlipsOptimalI(int[] arr, int k) {
        int n = arr.length;
        int operations = 0;
        int flipsFromPastAtIndex = 0;
        boolean[] isFlipped = new boolean[n]; // SC: O(N)
        Arrays.fill(isFlipped, false);
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (i >= k && isFlipped[i - k]) {
                // we have an extra flip from past so reduce it
                flipsFromPastAtIndex--;
            }
            if (flipsFromPastAtIndex % 2 == arr[i]) {
                /**
                 * we need a flip at index 'i'
                 * - if arr[i] is 1 then odd flips will make it 0 
                 * - if arr[i] is 0 then even flips will make it 0
                 */
                if (i + k > n) {
                    // not possible to flip
                    return -1;
                }
                isFlipped[i] = true;
                flipsFromPastAtIndex++;
                operations ++;
            }
        }
        return operations;
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC: O(K x N)
     * SC: O(1)
     * 
     * Time Limit Exceeded (1110 / 1115 testcases passed)
     */
    public int kBitFlipsBruteForce(int[] arr, int k) {
        int n = arr.length;
        int operations = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (arr[i] == 1) {
                continue;
            }
            if (i + k > n) {
                // we cannot flip it as window size k does not satisfy
                return -1;
            }
            for (int j = i; j <= i + k - 1 && j < n; j++) { // TC: O(K)
                arr[j] = arr[j] == 0 ? 1 : 0;
            }
            operations++;
        }
        return operations;
    }
}
