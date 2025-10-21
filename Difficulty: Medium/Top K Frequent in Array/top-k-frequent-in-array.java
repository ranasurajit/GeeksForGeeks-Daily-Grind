class Solution {
    /**
     * Approach : Using Hashing + Sorting Approach
     * 
     * TC: O(N) + O(N) + O(N x log(N)) + O(K) ~ O(N x log(N))
     * SC: O(N) + O(2 x N) ~ O(N)
     */
    public ArrayList<Integer> topKFreq(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int num : arr) { // TC: O(N)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int[][] freqArr = new int[freqMap.size()][2]; // SC: O(2 x N)
        int i = 0;
        for (Integer key : freqMap.keySet()) { // TC: O(N)
            freqArr[i][0] = key;
            freqArr[i][1] = freqMap.get(key);
            i++;
        }
        Arrays.sort(freqArr, (a, b) -> {
            if (b[1] == a[1]) {
                return b[0] - a[0];
            }
            return b[1] - a[1];
        }); // TC: O(N x log(N))
        ArrayList<Integer> result = new ArrayList<Integer>();
        int index = 0;
        while (k-- > 0) { // TC: O(K)
            result.add(freqArr[index][0]);
            index++;
        }
        return result;
    }
}
