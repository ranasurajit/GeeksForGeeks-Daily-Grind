class Solution {
    /**
     * Approach : Using Property of DAG Graphs
     * 
     * TC: O(E)
     * SC: O(E)
     */
    public int maxEdgesToAdd(int V, int[][] edges) {
        // the maximum possible edges in a DAG is V * (V - 1) / 2
        long maxEdges = ((long) V * ((long) V - 1)) / 2;
        Set<String> edgeSet = new HashSet<String>(); // SC: O(E)
        for (int[] edge : edges) { // TC: O(E)
            edgeSet.add(edge[0] + "-" + edge[1]);
        }
        // number of edges that can be safely (to keep the graph DAG) is below
        return (int) (maxEdges - edgeSet.size());
    }
}
