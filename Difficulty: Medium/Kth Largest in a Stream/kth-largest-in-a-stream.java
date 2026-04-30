class Solution {
    /**
     * Approach : Using Min-Heap Approach
     * 
     * TC : O(n x log(k))
     * SC : O(k)
     */
    static ArrayList<Integer> kthLargest(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // SC : O(k)
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (pq.isEmpty() || pq.size() < k) {
                pq.offer(arr[i]);     // TC : O(log(k))
                if (pq.size() == k) {
                    result.add(pq.peek());
                } else {
                    result.add(-1);
                }
            } else {
                // here the Min-Heap has size k
                if (arr[i] > pq.peek()) {
                    pq.poll();
                    pq.offer(arr[i]); // TC : O(log(k))
                }
                result.add(pq.peek());
            }
        }
        return result;
    }
}
