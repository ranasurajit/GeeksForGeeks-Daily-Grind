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
    /**
     * Approach : Using BFS + DFS Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public int minTime(Node root, int target) {
        Map<Integer, ArrayList<Integer>> adj =
            convertTreeToGraph(root); // TC: O(N), SC: O(N)
        Map<Integer, Boolean> visited = new HashMap<>(); // SC: O(N)
        for (Integer key : adj.keySet()) { // TC: O(N)
            visited.put(key, false);
        }
        Queue<Pair> queue = new LinkedList<>(); // SC: O(N)
        queue.offer(new Pair(0, target));
        visited.put(target, true);
        int minTime = 0;
        while (!queue.isEmpty()) { // TC: O(N)
            Pair current = queue.poll();
            int time = current.time;
            int u = current.node;
            minTime = Math.max(minTime, time);
            for (Integer v : adj.getOrDefault(u, new ArrayList<>())) {
                if (!visited.get(v)) {
                    queue.offer(new Pair(time + 1, v));
                    visited.put(v, true);
                }
            }
        }
        return minTime;
    }
    
    /**
     * Using DFS Approach to convert Binary Tree to Bi-directional Graph
     * 
     * TC: O(N)
     * SC: O(N) + O(H) ~ O(N)
     */
    private Map<Integer, ArrayList<Integer>> convertTreeToGraph(Node root) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>(); // SC: O(N)
        dfsTree(root, adj); // TC: O(N), SC: O(H)
        return adj;
    }
    
    /**
     * Using DFS Approach
     * 
     * TC: O(N)
     * SC: O(H)
     */
    private void dfsTree(Node root, Map<Integer, ArrayList<Integer>> adj) {
        if (root == null) {
            return;
        }
        adj.computeIfAbsent(root.data, k -> new ArrayList<>());
        if (root.left != null) {
            adj.get(root.data).add(root.left.data);
            adj.computeIfAbsent(root.left.data,
                k -> new ArrayList<>()).add(root.data);
        }
        if (root.right != null) {
            adj.get(root.data).add(root.right.data);
            adj.computeIfAbsent(root.right.data,
                k -> new ArrayList<>()).add(root.data);
        }
        dfsTree(root.left, adj);
        dfsTree(root.right, adj);
    }
}

class Pair {
    int time;
    int node;
    
    public Pair (int time, int node) {
        this.time = time;
        this.node = node;
    }
}
