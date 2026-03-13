/*
class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}
*/
class Solution {
    private int minPos = Integer.MAX_VALUE;
    private int maxPos = Integer.MIN_VALUE;
    
    /**
     * Approach : Using DFS Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(H) ~ O(N)
     * 
     * where H = height of Binary Tree (worst case H ~ N)
     */
    public ArrayList<Integer> topView(Node root) {
        Map<Integer, Pair> map = new HashMap<>(); // SC: O(N)
        dfsTree(root, 0, 0, map); // TC: O(N), SC: O(H)
        ArrayList<Integer> view = new ArrayList<>();
        for (int key = minPos; key <= maxPos; key++) { // TC: O(N)
            view.add(map.get(key).node.data);
        }
        return view;
    }
    
    /**
     * Using DFS Approach
     * 
     * TC: O(N)
     * SC: O(H)
     */
    private void dfsTree(Node node, int position, int level, Map<Integer, Pair> map) {
        // Base Case
        if (node == null) {
            return;
        }
        // Recursion Calls
        if (!map.containsKey(position)) {
            map.put(position, new Pair(level, node));
        } else {
            Pair current = map.get(position);
            if (current.level > level) {
                map.put(position, new Pair(level, node));
            }
        }
        minPos = Math.min(minPos, position);
        maxPos = Math.max(maxPos, position);
        dfsTree(node.left, position - 1, level + 1, map);
        dfsTree(node.right, position + 1, level + 1, map);
    }
}

class Pair {
    int level;
    Node node;
    
    public Pair(int level, Node node) {
        this.level = level;
        this.node = node;
    }
}
