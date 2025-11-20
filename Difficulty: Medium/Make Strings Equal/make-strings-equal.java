class Solution {
    private static int INF = (int) 1e9 + 7;

    /**
     * Approach : Using Dijkstra's Algorithm Approach
     * 
     * TC: O(26) + O(26 x N) ~ O(N)
     * SC: O(1)
     */
    public int minCost(String s, String t, char[][] transform, int[] cost) {
        int n = s.length();
        Map<Integer, ArrayList<int[]>> adj =
            createGraph(transform, cost); // TC: O(E), SC: O(E)
        int[][] minCostData = new int[26][26];
        for (int i = 0; i < 26; i++) { // TC: O(26)
            minCostData[i] = dijkstraCost(adj, i); // TC: O(1)
        }
        int p = 0; // start pointer at String 's'
        int q = 0; // start pointer at String 't'
        int totalMinCost = 0;
        while (p < n && q < n) { // TC: O(N)
            if (s.charAt(p) != t.charAt(q)) {
                int a = s.charAt(p) - 'a';
                int b = t.charAt(q) - 'a';
                int minimumCost = Integer.MAX_VALUE;
                for (int i = 0; i < 26; i++) { // TC: O(26)
                    if (minCostData[a][i] != INF && minCostData[b][i] != INF) {
                        minimumCost = Math.min(minimumCost, minCostData[a][i] + minCostData[b][i]);
                    }
                }
                if (minimumCost == Integer.MAX_VALUE) {
                    return -1;
                }
                totalMinCost += minimumCost;
            }
            p++;
            q++;
        }
        return totalMinCost;
    }
    
    /**
     * Using Dijkstra's Algorithm Approach
     * 
     * TC: O((V + E) x log(V)) ~ O(1), where V = 26 and E = 26 x 26
     * SC: O(V) + O(V) ~ O(1)
     */
    private int[] dijkstraCost(Map<Integer, ArrayList<int[]>> adj, int src) {
        int[] minCostVal = new int[26]; // SC: O(26)
        Arrays.fill(minCostVal, INF);
        minCostVal[src] = 0;
        // we will be storing { cost, node } in Min-Heap
        PriorityQueue<int[]> pq = 
            new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(26)
        pq.offer(new int[] { 0, src });
        while (!pq.isEmpty()) { // TC: O()
            int[] current = pq.poll();
            int w = current[0];
            int u = current[1];
            if (w > minCostVal[u]) {
                continue;
            }
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) {
                int v = ngbr[0];
                int edgeCost = ngbr[1];
                if (w + edgeCost < minCostVal[v]) {
                    minCostVal[v] = w + edgeCost;
                    pq.offer(new int[] { w + edgeCost, v });
                }
            }
        }
        return minCostVal;
    }
    
    /**
     * Using Hashing Approach
     * 
     * TC: O(E)
     * SC: O(E + 26) ~ O(E)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(char[][] transform, int[] cost) {
        Map<Integer, ArrayList<int[]>> adj = 
            new HashMap<Integer, ArrayList<int[]>>();
        for (int i = 0; i < transform.length; i++) { // TC: O(E)
            int u = transform[i][0] - 'a';
            int v = transform[i][1] - 'a';
            int w = cost[i];
            adj.computeIfAbsent(u, k -> new ArrayList<int[]>()).add(new int[] { v, w });
        }
        return adj;
    }
}
