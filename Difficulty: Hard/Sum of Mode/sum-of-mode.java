class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) Approach
     * 
     * TC: O(N x log(K))
     * SC: O(N + K)
     */
    public int sumOfModes(int[] arr, int k) {
        int n = arr.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        // k is the size of the sliding window
        // we will store the frequencies of elements of array 'arr' in HashMap
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>(); // SC: O(N)
        // we will store <Pair, Integer> in a TreeMap sorted as per frequency
        // Treemap will store <Pair(Element, Frequency), Element>
        TreeMap<Pair, Integer> freqTreeMap =
            new TreeMap<Pair, Integer>((a, b) -> {
                if (a.freq == b.freq) {
                    return a.element - b.element;
                }
                return b.freq - a.freq;
            }); // SC: O(K)
        int sumModes = 0;
        while (j < n) { // TC: O(N)
            // adding arr[j] to HashMap
            freq.put(arr[j], freq.getOrDefault(arr[j], 0) + 1);
            if (freq.get(arr[j]) > 1) {
                // previously arr[j] was contained in HashMap 'freq', so delete mapping of arr[j] from freqTreeMap
                freqTreeMap.remove(new Pair(arr[j], freq.get(arr[j]) - 1)); // TC: O(log(K))
            }
            freqTreeMap.put(new Pair(arr[j], freq.get(arr[j])), arr[j]); // TC: O(log(K))
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                sumModes += freqTreeMap.firstEntry().getValue();
                // remove computation from index 'i'
                int freqCount = freq.get(arr[i]);
                if (freqCount == 1) {
                    // remove arr[i] from HashMap and TreeMap
                    freq.remove(arr[i]);
                    freqTreeMap.remove(new Pair(arr[i], freqCount)); // TC: O(log(K))
                } else {
                    // decrement frequency in both HashMap and TreeMap
                    freq.put(arr[i], freqCount - 1);
                    freqTreeMap.remove(new Pair(arr[i], freqCount)); // TC: O(log(K))
                    freqTreeMap.put(new Pair(arr[i], freqCount - 1), arr[i]); // TC: O(log(K))
                }
                // slide to next window
                i++;
                j++;
            }
        }
        return sumModes;
    }
    
    class Pair {
        int element;
        int freq;
        
        public Pair (int element, int freq) {
            this.element = element;
            this.freq = freq;
        }
    }
}
