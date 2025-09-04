/*
class Node
{
    int data;
    Node next;
    Node(int key)
    {
        data = key;
        next = null;
    }
}
*/

class Solution {
    /**
     * Approach : Using Two Pointers + Recursion Approach
     * 
     * TC: O(N + K) x O(N / K)
     * SC: O(N / K)
     */
    public Node reverseKGroup(Node head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        Node prev = null;
        Node current = head;
        int step = k;
        while (step > 0 && current != null) { // TC: O(K)
            prev = current;
            current = current.next;
            step--;
        }
        prev.next = null;
        Node reverseHead = reverseLL(head);   // TC: O(N)
        Node tail = getTailNode(reverseHead); // TC: O(N)
        tail.next = reverseKGroup(current, k);
        return reverseHead;
    }

    /**
     * Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private Node getTailNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node prev = null;
        Node current = head;
        while (current != null) { // TC: O(N)
            prev = current;
            current = current.next;
        }
        return prev;
    }
    
    /**
     * Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private Node reverseLL(Node head) {
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
