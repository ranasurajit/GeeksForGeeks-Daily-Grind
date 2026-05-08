class Solution {
    /**
     * Approach : Using BFS Approach
     * 
     * TC : O(n²) + O(n x log(n)) ~ O(n²)
     * SC : O(n) + O(n) ~ O(n)
     */
    public List<String> validParenthesis(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return result;
        }
        Queue<String> queue = new LinkedList<>(); // SC : O(n)
        Set<String> visited = new HashSet<>(); // SC : O(n)
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) { // TC : O(n)
            String current = queue.poll();
            if (isValid(current)) { // TC : O(n)
                result.add(current);
                found = true;
            }
            if (found) {
                continue;
            }
            for (int i = 0; i < current.length(); i++) {
                char ch = current.charAt(i);
                if (ch != '(' && ch != ')') {
                    continue;
                }
                String next = current.substring(0, i) +
                    current.substring(i + 1);
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
        // sort for lexographically sorted order
        Collections.sort(result); // TC : O(n x log(n))
        if (result.isEmpty()) {
            result.add("");
        }
        return result;
    }
 
    /**
     * Using Simulation Approach
     * 
     * TC : O(n)
     * SC : O(1)
     */
    private boolean isValid(String s) {
        /**
         * we will have a variable 'open' for '('
         * and close for ')'. For a valid string
         * we should always have close <= open
         */
        int open = 0;
        int close = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) { // TC : O(n)
            char ch = s.charAt(i);
            if (ch == '(') {
                open++;
            } else if (ch == ')') {
                close++;
                if (close > open) {
                    return false;
                }
            }
        }
        return open == close;
    }
}
