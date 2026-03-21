class Solution {
    /**
     * Approach : Using BST Property + Math Approach
     * 
     * TC: O(N²) + O(N²) ~ O(N²)
     * SC: O(N)
     */
    public ArrayList<Integer> countBSTs(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> counts = new ArrayList<>();
        int[] catalan = getCatalanNumber(n); // TC: O(N²), SC: O(N)
        for (int i = 0; i < n; i++) {        // TC: O(N)
            int left = 0;
            int right = 0;
            for (int j = 0; j < n; j++) {    // TC: O(N)
                if (arr[j] < arr[i]) {
                    left++;
                } else if (arr[j] > arr[i]) {
                    right++;
                }
            }
            int leftBSTCount = catalan[left];
            int rightBSTCount = catalan[right];
            counts.add(leftBSTCount * rightBSTCount);
        }
        return counts;
    }
    
    /**
     * Using Math Approach
     * 
     * TC: O(N²)
     * SC: O(N)
     */
    private int[] getCatalanNumber(int N) {
        int[] catalan = new int[N + 1];   // SC: O(N)
        catalan[0] = 1;
        catalan[1] = 1;
        for (int i = 2; i <= N; i++) {    // TC: O(N)
            for (int j = 0; j < i; j++) { // TC: O(N)
                catalan[i] += catalan[j] * catalan[i - j - 1];
            }
        }
        return catalan;
    }
}
