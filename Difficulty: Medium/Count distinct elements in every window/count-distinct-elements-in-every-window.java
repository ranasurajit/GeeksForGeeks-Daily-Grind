class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) Approach
     * 
     * TC: O(N)
     * SC: O(K)
     */
    ArrayList<Integer> countDistinct(int arr[], int k) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(K)
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (j < n) { // TC: O(N)
            map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
            if (j - i + 1 == k) {
                result.add(map.size());
                // remove computation from index 'i'
                int freq = map.get(arr[i]);
                if (freq == 1) {
                    // remove arr[i] from map
                    map.remove(arr[i]);
                } else {
                    map.put(arr[i], map.get(arr[i]) - 1);
                }
                // slide to next window
                i++;
            }
            j++;
        }
        return result;
    }
}
