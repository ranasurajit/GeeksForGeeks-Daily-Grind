/*
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    int minOffset = Integer.MAX_VALUE;
    int maxOffset = Integer.MIN_VALUE;

    /**
     * Approach : Using BFS Traversal Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        ArrayList<ArrayList<Integer>> view = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return view;
        }
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // SC: O(N)
        Queue<Pair> queue = new LinkedList<>(); // SC: O(N)
        queue.offer(new Pair(0, root));
        while (!queue.isEmpty()) { // TC: O(N)
            Pair current = queue.poll();
            int offset = current.offset;
            Node currentNode = current.node;
            minOffset = Math.min(minOffset, offset);
            maxOffset = Math.max(maxOffset, offset);
            // make an entry to the HashMap with offset as key
            map.computeIfAbsent(offset, k -> new ArrayList<>()).add(currentNode.data);
            if (currentNode.left != null) {
                queue.offer(new Pair(offset - 1, currentNode.left));
            }
            if (currentNode.right != null) {
                queue.offer(new Pair(offset + 1, currentNode.right));
            }
        }
        for (int key = minOffset; key <= maxOffset; key++) { // TC: O(N)
            view.add(map.get(key));
        }
        return view;
    }
}

class Pair {
    int offset;
    Node node;
    
    public Pair(int offset, Node node) {
        this.offset = offset;
        this.node = node;
    }
}
