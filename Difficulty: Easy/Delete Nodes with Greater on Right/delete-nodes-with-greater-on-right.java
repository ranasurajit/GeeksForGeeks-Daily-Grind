/* Structure of linked list node
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
     * Approach : Using Two Pointers Approach
     * 
     * TC : O(n) + O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    Node compute(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        head = reverseLL(head); // TC : O(n), SC : O(1)
        Node current = head;
        int currentMax = head.data;
        while (current != null && current.next != null) { // TC : O(n)
            if (current.next.data < currentMax) {
                current.next = current.next.next;
            } else {
                current = current.next;
                currentMax = current.data;
            }
        }
        return reverseLL(head); // TC : O(n), SC : O(1)
    }
    
    /**
     * Using Two Pointers Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    private Node reverseLL(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node prev = null;
        Node current = head;
        while (current != null) {
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }
}
