/*
class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
*/
class Solution {
    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node prev = null;
        Node current = head;
        while (current != null) { // TC: O(N)
            Node temp = current.next;
            current.next = prev;
            current.prev = temp;
            prev = current;
            current = temp;
        }
        return prev;
    }
}
