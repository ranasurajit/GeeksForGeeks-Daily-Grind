class Solution {
    /**
     * Approach II : Using Two Pointers + Sorting Approach
     * 
     * TC: O(N) + O(N x log(N)) + O(N) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(1)
     */
    public void sortIt(int[] arr) {
        int n = arr.length;
        int i = 0;
        int j = n - 1;
        // we will swap array 'arr' to have { ...odd elements, ...even elements)
        while (i <= j) {                      // TC: O(N)
            if ((arr[i] & 1) != 0) {
                // arr[i] is odd so increment i
                i++;
            } else {
                // swap arr[i] and arr[j] and decrement j
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j--;
            }
        }
        Arrays.sort(arr, 0, i);          // TC: O(N x log(N))
        reverseArray(arr, 0, i - 1);     // TC: O(N)
        Arrays.sort(arr, i, n);          // TC: O(N x log(N))
    }

    /**
     * Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private void reverseArray(int[] arr, int start, int end) {
        while (start < end) { // TC: O(N)
            // swap arr[i] and arr[j] and decrement j
            int temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;
            start++;
            end--;
        }
    }

    /**
     * Approach I : Using Sorting Approach
     * 
     * TC: O(N x log(N)) + O(N) + O(N1 + N2) ~ O(N x log(N))
     * SC: O(N1 + N2) ~ O(N)
     * 
     * where N1 + N2 = N
     */
    public void sortItUsingSorting(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr); // TC: O(N x log(N))
        List<Integer> oddList = new ArrayList<Integer>(); // SC: O(N1)
        List<Integer> evenList = new ArrayList<Integer>(); // SC: O(N1)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if ((arr[i]  & 1) == 0) {
                // even
                evenList.add(arr[i]);
            }
            if ((arr[n - i - 1] & 1) != 0) {
                // odd
                oddList.add(arr[n - i - 1]);
            }
        }
        int startIdx = 0;
        while (startIdx < oddList.size()) { // TC: O(N1)
            arr[startIdx] = oddList.get(startIdx);
            startIdx++;
        }
        int offset = startIdx;
        while (startIdx < n) { // TC: O(N2)
            arr[startIdx] = evenList.get(startIdx - offset);
            startIdx++;
        }
    }
}
