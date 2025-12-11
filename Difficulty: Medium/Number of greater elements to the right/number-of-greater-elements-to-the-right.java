
class Solution {
    /**
     * Approach : Using Brute-Force Approach
     *
     * TC: O(Q x (N - Q)) ~ O(Q x N)
     * SC: O(1)
     */
    public static int[] count_NGE(int arr[], int indices[]) {
        int n = arr.length;
        int q = indices.length;
        int[] result = new int[q];
        for (int i = 0; i < q; i++) { // TC: O(Q)
            int count = 0;
            for (int j = indices[i] + 1; j < n; j++) { // TC: O(N - Q)
                if (arr[j] > arr[indices[i]]) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }
}
