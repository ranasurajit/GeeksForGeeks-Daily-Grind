class Solution {
    /**
     * Approach II : Using Optimal (Without Extra Space) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public ArrayList<Integer> findDuplicates(int[] arr) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int n = arr.length;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int x = Math.abs(arr[i]);
            int index = x - 1;
            if (arr[index] < 0) {
                result.add(x);
            } else {
                arr[index] = -1 * arr[index];
            }
        }
        return result;
    }

    /**
     * Approach I : Using Hashing (With Extra Space) Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public ArrayList<Integer> findDuplicatesBruteForce(int[] arr) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>(); // SC: O(N)
        for (int num : arr) { // TC: O(N)
            if (!set.add(num)) {
                result.add(num);
            }
        }
        return result;
    }
}
