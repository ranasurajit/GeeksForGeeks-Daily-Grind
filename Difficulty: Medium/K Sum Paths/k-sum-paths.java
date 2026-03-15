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
     * Approach II : Using DFS + Hashing Approach
     * 
     * TC: O(N)
     * SC: O(N) + O(H) ~ O(N)
     */
    public int countAllPaths(Node root, int k) {
        // we will be computing and storing prefixSum in HashMap
        Map<Integer, Integer> map = new HashMap<>(); // SC: O(N)
        map.put(0, 1); // needed if start indices has sum = k
        return solveDFS(root, 0, map, k); // TC: O(N), SC: O(H)
    }
    
    /**
     * Using DFS Approach
     * 
     * TC: O(N)
     * SC: O(H)
     */
    private int solveDFS(Node node, int sum, Map<Integer, Integer> map, int k) {
        // Base Case
        if (node == null) {
            return 0;
        }
        // Recursion Calls
        sum += node.data;
        int count = 0;
        if (map.containsKey(sum - k)) {
            count += map.get(sum - k);
        }
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        count += solveDFS(node.left, sum, map, k);
        count += solveDFS(node.right, sum, map, k);
        int freq = map.get(sum);
        if (freq == 1) {
            map.remove(sum);
        } else {
            map.put(sum, freq - 1);
        }
        return count;
    }

    /**
     * Approach I : Using Brute-Force (DFS) Approach
     * 
     * TC: O(N²)
     * SC: O(H) ~ O(N)
     */
    public int countAllPathsBruteForce(Node root, int k) {
        // Base Case
        if (root == null) {
            return 0;
        }
        // count paths that include Node 'root'
        int countIncludingCurrent = countFromNode(root, k); // TC: O(N), SC: O(H)
        // Recursion Calls
        int countExcludingCurrent = 
            countAllPaths(root.left, k) +
            countAllPaths(root.right, k);
        return countIncludingCurrent + countExcludingCurrent;
    }
    
    /**
     * Using DFS Approach
     * 
     * TC: O(N)
     * SC: O(H)
     */
    private int countFromNode(Node node, int remainingSum) {
        // Base Case
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.data == remainingSum) {
            // we got a path here
            count++;
        }
        // Recursion Calls
        count += countFromNode(node.left, remainingSum - node.data);
        count += countFromNode(node.right, remainingSum - node.data);
        return count;
    }
}
