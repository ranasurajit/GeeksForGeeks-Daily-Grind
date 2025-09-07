/*
class Node {
    int data;
    Node next;

    Node(int key) {
        data = key;
        next = null;
    }
}
*/

class Solution {
    /**
     * Approach : Using Merge Sort + Two Pointers Approach
     * 
     * TC: O(N x log(N))
     * SC: O(log(N)) - recursion stack
     */
    public Node mergeSort(Node head) {
        // Base Case
        if (head == null || head.next == null) {
            // if head is single element or is null return itself
            return head;
        }
        Node middle = middleLL(head); // TC: O(N / 2)
        Node left = head;
        Node right = middle.next;
        middle.next = null; // separating both LinkedList
        Node sortedLeft = mergeSort(left); 
        Node sortedRight = mergeSort(right);
        // now after we have the sorted Linked-Lists, we will merge them back
        return mergeSortedLinkedList(sortedLeft, sortedRight); // TC: O(N)
    }
    
    /**
     * Using Fast and Slow Pointers Approach
     * 
     * TC: O(N / 2)
     * SC: O(1)
     */
    private Node middleLL(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node prev = null;
        Node slow = head;
        Node fast = head;
        /**
         * Move slow pointer by 1 step and fast pointer by 2 steps
         */
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return prev;
    }
    
    /**
     * Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private Node mergeSortedLinkedList(Node list1, Node list2) {
        Node dummy = new Node(-1);
        Node current = dummy;
        while (list1 != null && list2 != null) { // TC: O(M + N)
            if (list1.data <= list2.data) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
        }
        return dummy.next;
    }
}
