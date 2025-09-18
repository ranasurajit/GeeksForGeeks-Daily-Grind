class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(2 x N) + O(N) ~ O(N)
     * SC: O(2 x N) + O(2 x N) + O(2 x N) ~ O(N)
     */
    public ArrayList<Integer> nextGreater(int[] arr) {
        int n = arr.length;
        int[] cirArr = new int[2 * n]; // SC: O(2 x N)
        for (int i = 0; i < n; i++) {  // TC: O(N)
            cirArr[i] = arr[i];
            cirArr[n + i] = arr[i];
        }
        Stack<Integer> st = new Stack<Integer>(); // SC: O(2 x N)
        int[] nge = new int[2 * n]; // SC: O(2 x N)
        for (int i = 2 * n - 1; i >= 0; i--) { // TC: O(2 x N)
            while (!st.isEmpty() && st.peek() <= cirArr[i]) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(cirArr[i]);
        }
        ArrayList<Integer> ngeList = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            ngeList.add(nge[i]);
        }
        return ngeList;
    }
}
