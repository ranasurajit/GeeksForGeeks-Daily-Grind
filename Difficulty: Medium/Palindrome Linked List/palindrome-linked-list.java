/*
class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}*/

class Solution {
    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC: O(N / 2) + O(N / 2) + O(N / 2) ~ O(N)
     * SC: O(1)
     */
    public boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            // Linked List with null head or single node as head is always palindrome
            return true;
        }
        Node midNode = middleLL(head); // TC: O(N / 2)
        Node reverseHead = reverseLL(midNode); // TC: O(N / 2)
        // now using two pointers to compare node values of both LinkedList
        Node current = head;
        Node revCurrent = reverseHead;
        while (current != null && revCurrent != null) { // TC: O(N / 2)
            if (current.data != revCurrent.data) {
                // early return
                return false;
            }
            current = current.next;
            revCurrent = revCurrent.next;
        }
        return true;
    }
    
    /**
     * Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private Node middleLL(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) { // TC: O(N / 2)
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * Using Two Pointers Approach
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
        while (current != null) {
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }
}
