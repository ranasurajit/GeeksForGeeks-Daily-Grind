class Solution {
    /**
     * Approach II : Using Optimal (Sorting + Two Pointers) Approach
     * 
     * TC : O(n) + O(n x log(n)) ~ O(n x log(n))
     * SC : O(1)
     * 
     * Accepted (1114 / 1115 testcases passed)
     */
    public static int countPairs(int arr[], int k) {
        int n = arr.length;
        Arrays.sort(arr); // TC : O(n x log(n))
        int count = 0;
        int i = 0;
        int j = 1;
        while (i < n) { // TC : O(n)
            if (j <= i) {
                j++;
            }
            while (j < n && arr[j] - arr[i] < k) {
                j++;
            }
            count += (j - i - 1); // number of pairs formed with j
            i++;
        }
        return count;
    }

    /**
     * Approach I : Using Brute-Force (Sorting + Two Pointers) Approach
     * 
     * TC : O(n²) + O(n x log(n)) ~ O(n²)
     * SC : O(1)
     * 
     * Time Limit Exceeded (1114 / 1115 testcases passed)
     */
    public static int countPairsBruteForce(int arr[], int k) {
        int n = arr.length;
        Arrays.sort(arr); // TC : O(n x log(n))
        int count = 0;
        int i = 0;
        int j = 1;
        while (j < n) { // TC : O(n)
            while (j < n && arr[j] - arr[i] < k) {
                j++;
            }
            j--; // reverting last increment
            count += (j - i);
            i++; // TC : O(n)
            j = i + 1;
        }
        return count;
    }
}
