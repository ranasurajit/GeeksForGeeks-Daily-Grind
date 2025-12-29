/*
class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}
*/

class Solution {
    /**
     * Approach : Using Two Pointers + LinkedList Traversal Approach
     * 
     * TC: O(M + N) + O(Max(M, N)) + O(Max(M, N)) ~ O(M + N)
     * SC: O(1)
     */
    public Node addTwoLists(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        Node revHead1 = reverseLL(head1); // TC: O(M)
        Node revHead2 = reverseLL(head2); // TC: O(N)
        Node dummy = new Node(-1);
        Node current = dummy;
        Node current1 = revHead1;
        Node current2 = revHead2;
        int sum = 0;
        int carry = 0;
        while (current1 != null || current2 != null) { // TC: O(Max(M, N))
            sum = carry;
            if (current1 != null) {
                sum += current1.data;
                current1 = current1.next;
            }
            if (current2 != null) {
                sum += current2.data;
                current2 = current2.next;
            }
            int rem = sum % 10;
            carry = sum / 10;
            current.next = new Node(rem);
            current = current.next;
        }
        if (carry > 0) {
            Node newNode = new Node(carry);
            current.next = newNode;
        }
        Node head = reverseLL(dummy.next); // TC: O(Max(M, N))
        while (head.data == 0) {
            // removing leading zeroes
            head = head.next;
        }
        return head;
    }

    /**
     * Using LinkedList Traversal Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private Node reverseLL(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node prev = null;
        Node current = head;
        while (current != null) { // TC: O(N)
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }
}
