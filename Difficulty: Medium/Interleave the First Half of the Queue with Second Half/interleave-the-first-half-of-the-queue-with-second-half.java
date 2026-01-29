class Solution {
    /**
     * Approach : Using Queue Approach
     * 
     * TC: O(N / 2) + O(N / 2) + O(N / 2) ~ O(N)
     * SC: O(N / 2) + O(N / 2) ~ O(N)
     */
    public void rearrangeQueue(Queue<Integer> q) {
        int n = q.size();
        int half = n / 2; // Queue q[] is of even size
        Queue<Integer> a = new LinkedList<Integer>(); // SC: O(N / 2)
        Queue<Integer> b = new LinkedList<Integer>(); // SC: O(N / 2)
        int count = 0;
        while (count < half) { // TC: O(N / 2)
            a.offer(q.poll());
            count++;
        }
        while (count < n) {    // TC: O(N / 2)
            b.offer(q.poll());
            count++;
        }
        while (!a.isEmpty() || !b.isEmpty()) { // TC: O(N / 2)
            if (!a.isEmpty()) {
                q.offer(a.poll());
            }
            if (!b.isEmpty()) {
                q.offer(b.poll());
            }
        }
    }
}
