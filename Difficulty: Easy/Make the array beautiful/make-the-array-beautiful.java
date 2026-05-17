class Solution {
    /**
     * Approach : Using Stack Approach
     * 
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    List<Integer> makeBeautiful(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>(); // SC : O(n)
        for (int i = 0; i < n; i++) {      // TC : O(n)
            int current = arr[i] >= 0 ? 1 : -1;
            if (!st.isEmpty()) {
                int last  = st.peek() >= 0 ? 1 : -1;
                if (last * current < 0) {
                    st.pop();
                } else {
                    st.push(arr[i]);
                }
            } else {
                st.push(arr[i]);
            }
        }
        Integer[] result = new Integer[st.size()]; // SC : O(n)
        int idx = st.size() - 1;
        while (!st.isEmpty()) { // TC : O(n)
            result[idx] = st.pop();
            idx--;
        }
        return Arrays.asList(result);
    }
}
