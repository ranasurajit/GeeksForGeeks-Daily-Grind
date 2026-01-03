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
     * Approach III : Using Recursion + Merge-Sort Approach
     * 
     * TC: O(T)
     * SC: O(K) + O(T) ~ O(T)
     * 
     * and T = total nodes, K = number of columns of sorted LinkedLists
     * 
     * Intuition: We will use recursively start merging LinkedLists one by one
     */
    public Node flatten(Node root) {
        // Base Case
        if (root == null || root.next == null) {
            return root;
        }
        // Recursion Calls
        // Recursion Leap of Faith - Believe that flatten would return flattened List of right
        root.next = flatten(root.next);
        root = mergeTwoLists(root, root.next); // TC: O(T), SC: O(T)
        return root;
    }
    
    /**
     * Using Recursion + Merge Sort Approach
     * 
     * TC: O(M + N)
     * SC: O(M + N)
     */
    private Node mergeTwoLists(Node a, Node b) {
        if (a == null) {
            // b is already sorted
            return b;
        }
        if (b == null) {
            // a is already sorted
            return a;
        }
        Node result = null;
        if (a.data < b.data) {
            result = a;
            result.bottom = mergeTwoLists(a.bottom, b);
        } else {
            result = b;
            result.bottom = mergeTwoLists(a, b.bottom);
        }
        result.next = null;
        return result;
    }

    /**
     * Approach II : Using Min-Heap (Optimization on Heap) Approach
     * 
     * TC: O(T x log(K)) + O(K x log(K)) ~ O(T x log(K))
     * SC: O(K)
     * 
     * where K = number of node to the right of head (including) / columns
     * and T = total nodes = K x L where L is average of all bottom nodes
     * 
     * Intuition: Here we will push only heads of each column LinkedList
     */
    public Node flattenBetter(Node root) {
        // Base Case
        if (root == null || root.next == null) {
            return root;
        }
        PriorityQueue<Node> pq = new PriorityQueue<Node>((p, q) -> {
            return p.data - q.data;
        }); // SC: O(K)
        int k = 0;
        Node current = root;
        while (current != null) { // TC: O(K)
            Node next = current.next;
            current.next = null;
            pq.offer(current); // TC: O(log(K))
            current = next;
            k++;
        }
        Node dummy = new Node(-1);
        Node temp = dummy;
        while (!pq.isEmpty()) { // TC: O(K x L) - we visit all nodes
            Node node = pq.poll();
            if (node.bottom != null) {
                pq.offer(node.bottom); // TC: O(log(K))
            }
            temp.bottom = node;
            temp = temp.bottom;
        }
        return dummy.bottom;
    }

    /**
     * Approach I : Using Brute-Force (Min-Heap) Approach
     * 
     * TC: O(N x L x log(N x L)) + O(N x L) ~ O(N x L x log(N x L))
     * SC: O(N x L)
     * 
     * where N = number of node to the right of head (including)
     * and L = average number of lengths of bottom nodes including head
     */
    public Node flattenBruteForce(Node root) {
        // Base Case
        if (root == null || root.next == null) {
            return root;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // SC: O(N x L)
        Node current = root;
        while (current != null) { // TC: O(N x L)
            pq.offer(current.data); // TC: O(log(N x L))
            Node next = current.next;
            Node down = current.bottom;
            while (down != null) {
                pq.offer(down.data); // TC: O(log(N x L))
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
