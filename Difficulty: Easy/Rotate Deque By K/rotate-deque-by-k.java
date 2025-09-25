class Solution {
    /**
     * Approach : Using Deque Properties Approach
     * 
     * TC: O(K)
     * SC: O(1)
     */
    public static void rotateDeque(Deque<Integer> dq, int type, int k) {
        while (k > 0) { // TC: O(K)
            if (type == 1) {
                dq.offerFirst(dq.pollLast()); // TC: O(1)
            } else {
                dq.offerLast(dq.pollFirst()); // TC: O(1)
            }
            k--;
        }
    }
}
