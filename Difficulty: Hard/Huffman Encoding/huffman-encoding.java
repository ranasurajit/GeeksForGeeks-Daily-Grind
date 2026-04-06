class Solution {
    /**
     * Approach : Using Min-Heap + DFS Approach
     * 
     * TC: O(n x log(n)) + O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC: O(n) + O(n) ~ O(n)
     */
    public ArrayList<String> huffmanCodes(String s, int f[]) {
        int n = f.length;
        ArrayList<String> result = new ArrayList<String>();
        if (n == 1) {
            result.add("0");
            return result;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((p, q) -> {
           if (p.freq != q.freq) {
               return p.freq - q.freq;
           }
           return p.order - q.order;
        }); // SC: O(n)
        for (int i = 0; i < n; i++) { // TC: O(n)
            pq.offer(new Node(s.charAt(i), f[i], i, null, null)); // TC: O(log(n))
        }
        while (pq.size() > 1) { // TC: O(n)
            Node left = pq.poll();
            Node right = pq.poll();
            Node merged = new Node('\0',
                left.freq + right.freq,
                Math.min(left.order, right.order),
                left,
                right
            );
            pq.offer(merged); // TC: O(log(n))
        }
        Node root = pq.poll();
        dfsTree(root, "", result); // TC: O(n), SC: O(n)
        return result;
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(n)
     * SC: O(n)
     */
    private void dfsTree(Node root, String path, ArrayList<String> result) {
        // Base Case
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }
        // Recursion Calls
        dfsTree(root.left, path + "0", result);
        dfsTree(root.right, path + "1", result);
    }
    
    class Node {
        char ch;  // needed for leaf nodes
        int freq;
        int order;
        Node left;
        Node right;
        
        public Node (char ch, int freq, int order, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.order = order;
            this.left = left;
            this.right = right;
        }
    }
}
