class Solution {
    /**
     * Approach : Using Graph BFS Approach
     * 
     * TC: O(n + m)
     * SC: O(n + m)
     */
    public int minimumEdgeReversal(int[][] edges, int n, int src, int dst) {
        // Adjacency list
        List<Pair>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // For every edge u -> v:
        // u -> v costs 0
        // v -> u costs 1
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(new Pair(v, 0));
            graph[v].add(new Pair(u, 1));
        }

        // Distance = minimum number of reversals required
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> deque = new ArrayDeque<>();

        dist[src] = 0;
        deque.offerFirst(src);

        while (!deque.isEmpty()) {

            int u = deque.pollFirst();

            for (Pair next : graph[u]) {
                int v = next.node;
                int weight = next.cost;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;

                    // 0-cost edges go to the front
                    if (weight == 0) {
                        deque.offerFirst(v);
                    } 
                    // 1-cost edges go to the back
                    else {
                        deque.offerLast(v);
                    }
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
    
    static class Pair {
        int node;
        int cost;

        Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
