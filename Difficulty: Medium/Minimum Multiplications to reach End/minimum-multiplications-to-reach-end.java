class Solution {
    /**
     * Approach : Using Graph BFS Approach
     * 
     * TC : O(n x m)
     * SC : O(m) + O(m) ~ O(m)
     */
    public int minSteps(int[] arr, int start, int end) {
        int n = arr.length;
        /**
         * Intuition : since we can explore start by multiplying
         * start with elements within array 'arr' so, it behaves 
         * like exploring to multiple nodes unless node with value
         * 'end' is reached and we need to find minimum steps so
         * BFS is the only way to go
         */
        Set<Integer> visited = new HashSet<>();    // SC : O(m)
        Queue<Integer> queue = new LinkedList<>(); // SC : O(m)
        queue.offer(start);
        visited.add(start);
        int steps = 0;
        while (!queue.isEmpty()) { // TC : O(m)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer u = queue.poll();
                if (u == end) {
                    return steps;
                }
                for (int x : arr) { // TC : O(n)
                    int v = (u * x) % 1000;
                    if (!visited.contains(v)) {
                        visited.add(v);
                        queue.offer(v);
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
