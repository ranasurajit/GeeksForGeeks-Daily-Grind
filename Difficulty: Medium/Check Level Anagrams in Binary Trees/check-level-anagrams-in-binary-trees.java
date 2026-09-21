/* Structure of binary tree Node
class Node {
    int data;
    Node left, right;

    Node(int x) {
        data = x;
        left = right = null;
    }
}
*/

class Solution {
    /**
     * Approach : Using BFS Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public boolean areAnagrams(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        /**
         * we can perform BFS from roots of both the
         * Binary Trees to perform Level Order Traversal
         * we can store each levels as a Set in an
         * ArrayList
         */
        Queue<Node> queue1 = new LinkedList<>(); // SC : O(n)
        Queue<Node> queue2 = new LinkedList<>(); // SC : O(n)
        queue1.offer(root1);
        queue2.offer(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) { // TC : O(n)
            int size1 = queue1.size();
            int size2 = queue2.size();
            if (size1 != size2) {
                // nodes are not same in count at level
                return false;
            }
            Map<Integer, Integer> frequency = new HashMap<>();
            // add node values to frequency HashMap
            for (int i = 0; i < size1; i++) {
                Node current = queue1.poll();
                frequency.put(current.data,
                    frequency.getOrDefault(current.data, 0) + 1);
                if (current.left != null) {
                    queue1.offer(current.left);
                }
                if (current.right != null) {
                    queue1.offer(current.right);
                }
            }
            // remove node values from frequency HashMap
            for (int i = 0; i < size1; i++) {
                Node current = queue2.poll();
                if (!frequency.containsKey(current.data)) {
                    return false;
                }
                int freq = frequency.get(current.data);
                if (freq == 1) {
                    frequency.remove(current.data);
                } else {
                    frequency.put(current.data, freq - 1);
                }
                if (current.left != null) {
                    queue2.offer(current.left);
                }
                if (current.right != null) {
                    queue2.offer(current.right);
                }
            }
            // at all levels frequncy Map should have empty elements
            if (!frequency.isEmpty()) {
                return false;
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
