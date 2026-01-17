class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public ArrayList<Integer> nextFreqGreater(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int x : arr) { // TC: O(N)
            freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
        }
        int[] result = new int[n]; // SC: O(N)
        Stack<int[]> st = new Stack<int[]>(); // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && st.peek()[1] <= freqMap.get(arr[i])) {
                st.pop();
            }
            result[i] = st.isEmpty() ? -1 : st.peek()[0];
            st.push(new int[] { arr[i], freqMap.get(arr[i]) });
        }
        ArrayList<Integer> nfg = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            nfg.add(result[i]);
        }
        return nfg;
    }
}
