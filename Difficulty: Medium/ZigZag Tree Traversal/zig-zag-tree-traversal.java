/*
class Node {
    int data;
    Node left,right;
    Node(int d)
    {
        data=d;
        left=right=null;
    }
}
*/

class Solution {
    ArrayList<Integer> zigZagTraversal(Node root) {
        ArrayList<Integer> traversal = new ArrayList<Integer>();
	    if (root == null) {
	        return traversal;
	    }
	    ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	    Queue<Node> queue = new LinkedList<Node>();
	    queue.offer(root);
	    int direction = 0;
	    while (!queue.isEmpty()) {
	        ArrayList<Integer> temp = new ArrayList<Integer>();
	        int size = queue.size();
	        for (int i = 0; i < size; i++) {
	            Node current = queue.poll();
	            if (current.left != null) {
	                queue.offer(current.left);
	            }
	            if (current.right != null) {
	                queue.offer(current.right);
	            }
	            if (direction % 2 == 0) {
	                temp.add(current.data);
	            } else {
	                temp.add(0, current.data);
	            }
	        }
	        direction++;
	        list.add(temp);
	    }
	    for (ArrayList<Integer> subList : list) {
	        for (Integer it : subList) {
	            traversal.add(it);
	        }
	    }
	    return traversal;
    }
}
