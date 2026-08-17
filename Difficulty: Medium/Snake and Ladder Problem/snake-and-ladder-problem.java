class Solution {
    /**
     * Approach : Using Graph BFS Approach
     * 
     * TC : O(n²)
     * SC : O(n²)
     */
    public int minThrows(int n, int[] lad, int[] sn) {
        int total = n * n;
        int[] jump = new int[total + 1]; // SC : O(n²)
        Arrays.fill(jump, -1);
        // setting ladders on the board
        for (int i = 0; i < lad.length; i += 2) { // TC : O(l)
            jump[lad[i]] = lad[i + 1];
        }
        // setting snakes on the board
        for (int i = 0; i < sn.length; i += 2) { // TC : O(l)
            jump[sn[i]] = sn[i + 1];
        }
        Queue<Integer> queue = new LinkedList<>();  // SC : O(n²)
        boolean[] visited = new boolean[total + 1]; // SC : O(n²)
        int start = 1;
        queue.offer(start);
        visited[start] = true;
        int diceThrows = 0;
        while (!queue.isEmpty()) { // TC : O(n²)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (current == total) {
                    // reached destination
                    return diceThrows;
                }
                for (int dice = 1; dice <= 6; dice++) { // TC : O(6)
                    int next = dice + current;
                    if (next > total) {
                        continue;
                    }
                    if (jump[next] != -1) {
                        // this means it is a ladder or snake
                        next = jump[next];
                    }
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            diceThrows++;
        }
        return -1;
    }
}
