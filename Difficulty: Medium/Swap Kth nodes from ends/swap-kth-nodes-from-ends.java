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
     * TC: O(K) + O(N - K) = O(N)
     * SC: O(1)
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
