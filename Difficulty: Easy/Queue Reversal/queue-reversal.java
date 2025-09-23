class Solution {
    /**
     * Approach : Using Stack Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public void reverseQueue(Queue<Integer> q) {
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        while (!q.isEmpty()) { // TC: O(N)
            st.push(q.poll());
        }
        while (!st.isEmpty()) { // TC: O(N)
            q.offer(st.pop());
        }
    }
}
