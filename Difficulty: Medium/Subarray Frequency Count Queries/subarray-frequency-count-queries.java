class Solution {
    /**
     * Approach II : Using Optimal (Hashing + Binary Search) Approach
     * 
     * TC : O(n) + O(q x 2 x log(n)) ~ O(n + q x log(n))
     * SC : O(n)
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public ArrayList<Integer> freqInRange(int[] arr, int[][] queries) {
        int n = arr.length;
        /**
         * we will be storing the indices of any arr[i] in HashMap
         */
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int[] query : queries) { // TC : O(q)
            int l = query[0];
            int r = query[1];
            int x = query[2];
            if (map.containsKey(x)) {
                ArrayList<Integer> indices = map.get(x); // by default sorted
                int left = lowerBound(indices, l) - 1;   // TC : O(log(n))
                int right = upperBound(indices, r) - 1;  // TC : O(log(n))
                result.add(right - left);
            } else {
                result.add(0);
            }
        }
        return result;
    }
    
    /**
     * Using Binary Search (Lower Bound) Approach
     * 
     * TC : O(log(n))
     * SC : O(1)
     */
    private int lowerBound(ArrayList<Integer> list, int x) {
        int n = list.size();
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC : O(log(n))
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    /**
     * Using Binary Search (Upper Bound) Approach
     * 
     * TC : O(log(n))
     * SC : O(1)
     */
    private int upperBound(ArrayList<Integer> list, int x) {
        int n = list.size();
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC : O(log(n))
            int mid = low + (high - low) / 2;
            if (list.get(mid) > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     * 
     * TC : O(q x (r - l + 1)) ~ O(q x n)
     * SC : O(1)
     * 
     * Time Limit Exceeded (1110 / 1111 testcases passed)
     */
    public ArrayList<Integer> freqInRangeBruteForce(int[] arr, int[][] queries) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>();
        for (int[] query : queries) { // TC : O(q)
            int l = query[0];
            int r = query[1];
            int x = query[2];
            int count = 0;
            for (int i = l; i <= r; i++) { // TC : O(r - l + 1)
                if (arr[i] == x) {
                    count++;
                }
            }
            result.add(count);
        }
        return result;
    }
}
