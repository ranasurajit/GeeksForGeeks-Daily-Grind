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
     * Approach II : Using Two Pointers (Clean Approach) Approach
     * 
     * TC: O(N + M)
     * SC: O(1)
     */
    public Node intersectPoint(Node head1, Node head2) {
        Node curr1 = head1;
        Node curr2 = head2;
        while (curr1 != curr2) { // TC: O(2 x (N + M))
            curr1 = curr1 == null ? head2 : curr1.next;
            curr2 = curr2 == null ? head1 : curr2.next;
        }
        return curr1;
    }

    /**
     * Approach I : Using Two Pointers Approach
     * 
     * TC: O(N + M)
     * SC: O(1)
     */
    public Node intersectPointTwoPointers(Node head1, Node head2) {
        Node curr1 = head1;
        Node curr2 = head2;
        while (curr1 != null || curr2 != null) { // TC: O(2 x (N + M))
            if (curr1 != null && curr1.next != null) {
                curr1 = curr1.next;
            } else {
                curr1 = head2;
            }
            if (curr2 != null && curr2.next != null) {
                curr2 = curr2.next;
            } else {
                curr2 = head1;
            }
            if (curr1 == curr2) {
                return curr1;
            }
        }
        return null;
    }
}
