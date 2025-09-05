/*
class Node {
    int data;
    Node next;

    Node(int d)
    {
        data = d;
        next = null;
    }
}*/

class Solution {
    /**
     * Approach : Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public Node segregate(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node zeroHead = new Node(-1);
        Node zeroTail = zeroHead;
        Node oneHead = new Node(-1);
        Node oneTail = oneHead;
        Node twoHead = new Node(-1);
        Node twoTail = twoHead;
        
        Node current = head;
        while (current != null) { // TC: O(N)
            if (current.data == 0) {
                zeroTail.next = current;
                zeroTail = zeroTail.next;
            } else if (current.data == 1) {
                oneTail.next = current;
                oneTail = oneTail.next;
            } if (current.data == 2) {
                twoTail.next = current;
                twoTail = twoTail.next;
            }
            current = current.next;
        }
        zeroTail.next = oneHead.next != null ? oneHead.next : twoHead.next;
        oneTail.next = twoHead.next;
        twoTail.next = null;
        return zeroHead.next != null ? zeroHead.next : 
            oneHead.next != null ? oneHead.next : twoHead.next;
    }
}
