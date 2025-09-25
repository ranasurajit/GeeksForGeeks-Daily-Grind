class Solution {
    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public ArrayList<String> generateBinary(int n) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 1; i <= n; i++) { // TC: O(N)
            result.add(decimalToBinary(i)); // TC: O(1)
        }
        return result;
    }
    
    /**
     * Using Bit-Manipulation Approach
     * 
     * TC: O(32) + O(32) ~ O(1)
     * SC: O(1)
     */
    private String decimalToBinary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) { // TC: O(32)
            sb.append(((num >> i) & 1));
        }
        int startIdx = 0;
        for (int i = 0; i < 32; i++) { // TC: O(32)
            if (sb.charAt(i) == '1') {
                startIdx = i;
                break;
            }
        }
        return sb.toString().substring(startIdx);
    }
}
