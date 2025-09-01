class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) Approach
     * 
     * Intuition:
     * - We want the mode (most frequent element) of every subarray of size K.
     * - A sliding window helps us efficiently move across the array without recalculating everything.
     * - HashMap tracks frequencies of elements.
     * - TreeMap (with custom comparator) keeps elements sorted by:
     *      1. Higher frequency first.
     *      2. If frequencies tie → smaller element first.
     * - At every window, the top entry of TreeMap gives us the current mode.
     * - Add mode to sum, slide window, and update both maps.
     * 
     * Hint:
     * - Use HashMap for quick frequency lookup.
     * - Use TreeMap to quickly fetch "highest frequency + smallest element".
     * - Remember: when sliding, carefully update both structures (remove old element, add new one).
     * 
     * TC: O(N x log(K)) 
     *       because TreeMap insert/remove/search costs log(K).
     * SC: O(N + K)
     *       for HashMap (N) and TreeMap (K).
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
