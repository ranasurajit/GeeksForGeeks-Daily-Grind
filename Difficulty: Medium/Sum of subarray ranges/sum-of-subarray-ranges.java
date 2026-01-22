class Solution {
    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * TC: O(4 x N) + O(N) ~ O(N)
     * SC: O(4 x N) + O(N) ~ O(N)
     */
    public int subarrayRanges(int[] arr) {
        int n = arr.length;
        int[] pse = previousSmallerElement(arr, n); // TC: O(N), SC: O(N)
        int[] nse = nextSmallerElement(arr, n);     // TC: O(N), SC: O(N)
        int[] pge = previousGreaterElement(arr, n); // TC: O(N), SC: O(N)
        int[] nge = nextGreaterElement(arr, n);     // TC: O(N), SC: O(N)
        int sumRanges = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            /**
             * we need to check in how many sub-arrays arr[i] is minimum or maximum
             * arr[i] is maximum when it is greater than elements to its left and right
             * so we need to find at index i,
             * 
             * case 1 : arr[i] is Maximum in index range (pge[i] + 1 to nge[i] - 1)
             * so left choices = i - pge[i], right choices = nge[i] - i 
             * so, total choices = left choices * right choices
             * 
             * similarly we compute for case 2
             * 
             * case 2: arr[i] is Minimum in index range (pse[i] + 1 to nse[i] - 1)
             * so left choices = i - pse[i], right choices = nse[i] - i 
             * so, total choices = left choices * right choices
             */
            int leftMax = i - pge[i];
            int rightMax = nge[i] - i;
            int totalMaxContribution = leftMax * rightMax;
            
            int leftMin = i - pse[i];
            int rightMin = nse[i] - i;
            int totalMinContribution = leftMin * rightMin;
            
            sumRanges += arr[i] * (totalMaxContribution - totalMinContribution);
        }
        return sumRanges;
    }

    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int[] previousSmallerElement(int[] arr, int n) {
        int[] pse = new int[n]; // SC: O(N)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pse;
    }
    
    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int[] nextSmallerElement(int[] arr, int n) {
        int[] nse = new int[n]; // SC: O(N)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return nse;
    }
    
    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int[] previousGreaterElement(int[] arr, int n) {
        int[] pge = new int[n]; // SC: O(N)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) {
                st.pop();
            }
            pge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pge;
    }
    
    /**
     * Using Monotonic Stack Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int[] nextGreaterElement(int[] arr, int n) {
        int[] nge = new int[n]; // SC: O(N)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return nge;
    }
}
