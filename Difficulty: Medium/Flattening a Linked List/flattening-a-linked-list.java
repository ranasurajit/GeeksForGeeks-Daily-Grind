/*
class Node {
    int data;
    Node next;
    Node bottom;

    Node(int x) {
        data = x;
        next = null;
        bottom = null;
    }
}
*/
class Solution {
    /**
     * Approach I : Using Min-Heap Approach
     * 
     * TC: O(N x L) + O(N x L) ~ O(N x L)
     * SC: O(N x L)
     * 
     * where N = number of node to the right of head (including)
     * and L = average number of lengths of bottom nodes including head
     */
    public Node flatten(Node root) {
        // Base Case
        if (root == null || (root.bottom == null && root.next == null)) {
            return root;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // SC: O(N x L)
        Node current = root;
        while (current != null) { // TC: O(N x L)
            pq.offer(current.data);
            Node next = current.next;
            Node down = current.bottom;
            while (down != null) {
                pq.offer(down.data);
                down = down.bottom;
            }
            current = next;
        }
        Node dummy = new Node(-1);
        Node temp = dummy;
        while (!pq.isEmpty()) { // TC: O(N x L)
            temp.bottom = new Node(pq.poll());
            temp = temp.bottom;
        }
        return dummy.bottom;
    }
}
