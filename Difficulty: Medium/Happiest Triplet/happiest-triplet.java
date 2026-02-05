class Solution {
    /**
     * Approach II : Using Optimal (Three Pointers) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * Time Limit Exceeded (10 / 1115 testcases passed)
     */
    int[] smallestDiff(int a[], int b[], int c[]) {
        int n = a.length;
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        /**
         * we will keep three pointers p, q and r all at 
         * index zero of respective arrays a, b and c
         */
        int p = 0;
        int q = 0;
        int r = 0;
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[3];
        while (p < n && q < n && r < n) { // TC: O(N)
            int minVal = Math.min(a[p], Math.min(b[q], c[r]));
            int maxVal = Math.max(a[p], Math.max(b[q], c[r]));
            int diff = maxVal - minVal;
            if (diff < minDiff) {
                minDiff = diff;
                result[0] = a[p];
                result[1] = b[q];
                result[2] = c[r];
            }
            // to reduce diff we need to increase index of array which equals minVal
            if (a[p] == minVal) {
                p++;
            } else if (b[q] == minVal) {
                q++;
            } else {
                r++;
            }
        }
        Arrays.sort(result); // TC: O(3 x log(3)) ~ O(1)
        reverse(result); // TC: O(1)
        return result;
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC: O(N³)
     * SC: O(N)
     * 
     * Time Limit Exceeded (10 / 1115 testcases passed)
     */
    int[] smallestDiffBruteForce(int a[], int b[], int c[]) {
        int n = a.length;
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[3];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) {         // TC: O(N)
            for (int j = 0; j < n; j++) {     // TC: O(N)
                for (int k = 0; k < n; k++) { // TC: O(N)
                    int minVal = Math.min(a[i], Math.min(b[j], c[k]));
                    int maxVal = Math.max(a[i], Math.max(b[j], c[k]));
                    if (minDiff > (maxVal - minVal)) {
                        minDiff = maxVal - minVal;
                        result[0] = a[i];
                        result[1] = b[j];
                        result[2] = c[k];
                        map.put(minDiff, a[i] + b[j] + c[k]);
                    } else if (minDiff == (maxVal - minVal) && 
                        map.get(maxVal - minVal) > a[i] + b[j] + c[k]) {
                        minDiff = maxVal - minVal;
                        result[0] = a[i];
                        result[1] = b[j];
                        result[2] = c[k];
                        map.put(minDiff, a[i] + b[j] + c[k]);
                    }
                }
            }
        }
        Arrays.sort(result); // TC: O(3 x log(3)) ~ O(1)
        reverse(result); // TC: O(1)
        return result;
    }
    
    /**
     * Using Two Pointers Approach
     * 
     * TC: O(3) ~ O(1)
     * SC: O(1)
     */
    private void reverse(int[] arr) {
        int p = 0;
        int q = 2;
        while (p < q) {
            int temp = arr[q];
            arr[q] = arr[p];
            arr[p] = temp;
            p++;
            q--;
        }
    }
}
