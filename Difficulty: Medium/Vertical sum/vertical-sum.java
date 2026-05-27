/* Structure of binary tree node
class Node{
public:
    int data;
    Node left, right;
    Node(int item)
    {
        data = item;
        left = right = null;
    }
}
*/
class Solution {
    /**
     * Approach : Using BFS Approach
     * 
     * TC : O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    public ArrayList<Integer> verticalSum(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Pair> queue = new LinkedList<>(); // SC : O(n)
        queue.offer(new Pair(root, 0));
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>(); // SC : O(n)
        while (!queue.isEmpty()) { // TC : O(n)
            Pair nodePair = queue.poll();
            Node node = nodePair.node;
            int offset = nodePair.offset;
            min = Math.min(min, offset);
            max = Math.max(max, offset);
            map.put(offset, map.getOrDefault(offset, 0) + node.data);
            if (node.left != null) {
                queue.offer(new Pair(node.left, offset - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, offset + 1));
            }
        }
        for (int i = min; i <= max; i++) {
            result.add(map.get(i));
        }
        return result;
    }
}

class Pair {
    Node node;
    int offset;
    
    public Pair(Node node, int offset) {
        this.node = node;
        this.offset = offset;
    }
}
