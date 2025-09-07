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
     * Approach : Using LinkedList Simulation + MinHeap Approach
     * 
     * TC: O(K x log(K)) + O(K x log(K)) ~ O(K x log(K))
     * SC: O(K)
     */
    Node mergeKLists(Node[] arr) {
        PriorityQueue<Node> pq = 
            new PriorityQueue<Node>((p, q) -> p.data - q.data); // SC: O(K)
        for (Node list : arr) { // TC: O(K)
            pq.offer(list);     // TC: O(log(K))
        }
        Node dummy = new Node(-1);
        Node current = dummy;
        while (!pq.isEmpty()) { // TC: O(K)
            Node temp = pq.poll();
            if (temp.next != null) {
                pq.offer(temp.next); // TC: O(log(K))
            }
            temp.next = null;
            current.next = temp;
            current = current.next;
        }
        return dummy.next;
    }
}
