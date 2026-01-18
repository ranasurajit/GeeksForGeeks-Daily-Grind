class Solution {
    /**
     * Approach II : Using Monotonic Deque Approach
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
        Deque<Integer> deque = new ArrayDeque<Integer>(); // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!deque.isEmpty() && freqMap.get(deque.peek()) <= freqMap.get(arr[i])) {
                deque.pop();
            }
            result[i] = deque.isEmpty() ? -1 : deque.peek();
            deque.push(arr[i]);
        }
        ArrayList<Integer> nfg = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            nfg.add(result[i]);
        }
        return nfg;
    }

    /**
     * Approach I : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public ArrayList<Integer> nextFreqGreaterUsingStack(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int x : arr) { // TC: O(N)
            freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
        }
        int[] result = new int[n]; // SC: O(N)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && freqMap.get(st.peek()) <= freqMap.get(arr[i])) {
                st.pop();
            }
            result[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }
        ArrayList<Integer> nfg = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            nfg.add(result[i]);
        }
        return nfg;
    }
}
