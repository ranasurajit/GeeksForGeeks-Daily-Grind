class Solution {
    public ArrayList<String> binstr(int n) {
        Set<String> result = new HashSet<String>();
        solveRecursion(0, n, new StringBuilder(), result);
        ArrayList<String> binaryStrings = new ArrayList<String>(result);
        Collections.sort(binaryStrings);
        return binaryStrings;
    }
    
    private void solveRecursion(int idx, int n, StringBuilder sb, Set<String> result) {
        // Base Case
        if (idx == n) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        // take 0 or 1
        sb.append('0');
        solveRecursion(idx + 1, n, sb, result);
        sb.setLength(sb.length() - 1);
        sb.append('1');
        solveRecursion(idx + 1, n, sb, result);
        sb.setLength(sb.length() - 1);
    }
}
