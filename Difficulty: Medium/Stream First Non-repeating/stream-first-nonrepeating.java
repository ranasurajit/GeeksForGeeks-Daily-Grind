class Solution {
    /**
     * Approach II : Using Queue + Hashing (Saving Some Space) Approach
     * 
     * TC: O(N)
     * SC: O(N) + O(N) + O(26) ~ O(N)
     */
    public String firstNonRepeating(String s) {
        int n = s.length();
        // as String s contains only lowercase alphabets, we can use freq array of size 26
        int[] freq = new int[26]; // SC: O(26) ~ O(1)
        /**
         * we need a data-structure which supports adding data at the 
         * end and polling from the front so Queue is the way to go
         */
        Queue<Character> queue = new ArrayDeque<Character>(); // SC: O(N)
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            freq[ch - 'a']++;
            queue.offer(ch);
            while (!queue.isEmpty() && freq[queue.peek() - 'a'] > 1) {
                queue.poll();
            }
            char c = queue.isEmpty() ? '#' : queue.peek();
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Approach I : Using Queue + Hashing Approach
     * 
     * TC: O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public String firstNonRepeatingUsingHashMapQueue(String s) {
        int n = s.length();
        Map<Character, Integer> freqMap = new HashMap<Character, Integer>(); // SC: O(N)
        /**
         * we need a data-structure which supports adding data at the 
         * end and polling from the front so Queue is the way to go
         */
        Queue<Character> queue = new LinkedList<Character>(); // SC: O(N)
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            queue.offer(ch);
            while (!queue.isEmpty() && freqMap.get(queue.peek()) > 1) {
                queue.poll();
            }
            char c = queue.isEmpty() ? '#' : queue.peek();
            sb.append(c);
        }
        return sb.toString();
    }
}
