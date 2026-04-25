class Solution {
    /**
     * Approach I : Using Stack Approach
     * 
     * TC : O(n)
     * SC : O(n)
     */
    public ArrayList<Integer> reducePairs(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>(); // SC : O(n)
        st.push(arr[0]);
        for (int i = 1; i < n; i++) {      // TC : O(n)
            int current = arr[i];
            boolean isAlive = true;
            while (!st.isEmpty() && (current * st.peek()) < 0) {
                // current and peek elements have opposite signs
                int prev = st.peek();
                if (Math.abs(current) == Math.abs(prev)) {
                    // both cancel each other
                    // prev dies and don't add current
                    st.pop();
                    isAlive = false;
                    break;
                }
                if (Math.abs(current) > Math.abs(prev)) {
                    // current should be live and prev dies
                    st.pop();
                    // current should be validated with remaining stack's top
                    continue;
                } else {
                    // current should die and prev is retained
                    isAlive = false;
                    break;
                }
            }
            if (isAlive) {
                st.push(current);
            }
        }
        return new ArrayList<Integer>(st);
    }
}
