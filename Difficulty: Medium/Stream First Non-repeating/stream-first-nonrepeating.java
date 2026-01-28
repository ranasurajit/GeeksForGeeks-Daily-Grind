class Solution {
    /**
     * Approach : Using Queue + Hashing Approach
     * 
     * TC: O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public String firstNonRepeating(String s) {
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
