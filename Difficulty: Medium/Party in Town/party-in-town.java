class Solution {

    static class Pair {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    private Pair bfs(int start, ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();

        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        int[] queue = new int[n];

        int front = 0;
        int rear = 0;

        queue[rear++] = start;
        visited[start] = true;

        int farthestNode = start;

        while (front < rear) {
            int u = queue[front++];

            for (int house : adj.get(u)) {
                // Houses are numbered 1 to n
                // Convert to 0-based index
                int v = house - 1;

                if (!visited[v]) {
                    visited[v] = true;
                    dist[v] = dist[u] + 1;
                    queue[rear++] = v;

                    if (dist[v] > dist[farthestNode]) {
                        farthestNode = v;
                    }
                }
            }
        }

        return new Pair(farthestNode, dist[farthestNode]);
    }

    public int partyHouse(ArrayList<ArrayList<Integer>> adj) {

        int n = adj.size();

        if (n <= 1) {
            return 0;
        }

        // First BFS: find one endpoint of diameter
        Pair first = bfs(0, adj);

        // Second BFS: find the diameter
        Pair second = bfs(first.node, adj);

        int diameter = second.dist;

        // Minimum maximum distance = radius
        return (diameter + 1) / 2;
    }
}
