class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public int maxPeople(int[] arr) {
        int n = arr.length;
        Stack<Pair> st = new Stack<Pair>(); // SC: O(N)
        int[] leftVisible = new int[n];     // SC: O(N)
        int[] rightVisible = new int[n];    // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int count = 0;
            while (!st.isEmpty() && st.peek().height < arr[i]) {
                // how many people popped out till arr[st.peek()] == arr[i]
                count += st.pop().count;
            }
            leftVisible[i] = count;
            st.push(new Pair(arr[i], count + 1)); // add 1 to include arr[i] itself
        }
        st.clear();
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            int count = 0;
            while (!st.isEmpty() && st.peek().height < arr[i]) {
                // how many people popped out till arr[st.peek()] == arr[i]
                count += st.pop().count;
            }
            rightVisible[i] = count;
            st.push(new Pair(arr[i], count + 1)); // add 1 to include arr[i] itself
        }
        int maxPeople = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            maxPeople = Math.max(maxPeople, leftVisible[i] + rightVisible[i] + 1);
        }
        return maxPeople;
    }
    
    static class Pair {
        int height;
        int count;
        Pair(int h, int c) {
            height = h;
            count = c;
        }
    }
}
