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
     * Approach : Using Two Pointers (Fast and Slow Pointers) Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(1)
     */
    public int lengthOfLoop(Node head) {
        if (head == null || head.next == null) {
            return 0;
        }
        Node slow = head;
        Node fast = head;
        // moving slow pointer by 1 step and fast pointer by 2 steps
        while (fast != null && fast.next != null) { // TC: O(N)
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // break where slow pointer meets fast pointer
                break;
            }
        }
        if (fast == null || fast.next == null) {
            // no loop present
            return 0;
        }
        /**
         * keep the slow pointer as is and start moving 
         * fast pointer by 1 step and keep counting length
         */
        fast = fast.next;
        int length = 1; // start from 1 as we moved fast pointer by 1 step
        while (fast != slow) { // TC: O(N)
            fast = fast.next;
            length++;
        }
        return length;
    }
}
