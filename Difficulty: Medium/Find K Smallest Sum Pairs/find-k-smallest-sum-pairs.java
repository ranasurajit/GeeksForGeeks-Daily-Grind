class Solution {
    /**
     * Approach : Using Min-Heap Approach
     * 
     * TC: O(K x log(K))
     * SC: O(K)
     */
    public ArrayList<ArrayList<Integer>> kSmallestPair(int[] arr1, int[] arr2, int k) {
        int m = arr1.length;
        int n = arr2.length;
        PriorityQueue<int[]> minHeap = 
            new PriorityQueue<int[]>((p, q) -> (p[0] + p[1]) - (q[0] + q[1])); // SC: O(K)
        Set<String> visited = new HashSet<String>();
        minHeap.offer(new int[] { arr1[0], arr2[0], 0, 0 });
        visited.add("0_0");
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        while (k-- > 0 && !minHeap.isEmpty()) { // TC: O(K)
            int[] current = minHeap.poll();
            result.add(new ArrayList<Integer>(Arrays.asList(current[0], current[1])));
            int i = current[2];
            int j = current[3];
            if (j + 1 < n && visited.add(i + "_" + (j + 1))) {
                minHeap.offer(new int[] { arr1[i], arr2[j + 1], i, j + 1 }); // TC: O(log(K))
            }
            if (i + 1 < m && visited.add((i + 1) + "_" + j)) {
                minHeap.offer(new int[] { arr1[i + 1], arr2[j], i + 1, j }); // TC: O(log(K))
            }
        }
        return result;
    }
}
