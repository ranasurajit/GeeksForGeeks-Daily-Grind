/*
class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}
*/
class Solution {
    /**
     * Approach : Using Two Pointers Approach
     *
     * Intuition:
     * - The kth node from the start can be found by moving forward k steps.
     * - The kth node from the end can be found by moving another pointer 
     *   (slow) while the first pointer (fast) finishes the list.
     * - Once both are found, swap their values.
     *
     * Hint:
     * - Use two pointers to locate kth from start and kth from end.
     * - Swap their data instead of restructuring the list.
     *
     * TC: O(K) + O(N - K) = O(N)
     *      (one traversal to reach kth start, another to reach kth end)
     * SC: O(1)
     *      (just pointers, no extra space)
     */
    public Node swapKth(Node head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        Node left = null;
        Node slow = head;
        Node fast = head;
        int position = k;
        while (fast != null && position > 0) { // TC: O(K)
            left = fast;
            fast = fast.next;
            position--;
        }
        if (position == 0) {
            while (slow != null && fast != null) { // TC: O(N - K)
                slow = slow.next;
                fast = fast.next;
            }
            Node right = slow;
            if (left != null && right != null) {
                // swapping the values of kth node from start and end
                int temp = right.data;
                right.data = left.data;
                left.data = temp;
            }
        }
        return head;
    }
}
