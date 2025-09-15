class Solution {
    /**
     * Approach : Using Stack + Simulation Approach
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * where N = size(arr)
     */
    public int evaluatePostfix(String[] arr) {
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        for (String s : arr) { // TC: O(N)
            if (!isOperator(s)) { // TC: O(1)
                st.push(Integer.valueOf(s));
            } else {
                int operand1 = Integer.valueOf(st.pop());
                int operand2 = Integer.valueOf(st.pop());
                int result = performOperation(operand1, operand2, s); // TC: O(1)
                st.push(result);
            }
        }
        return st.pop();
    }
    
    /**
     * Using Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private int performOperation(int operand1, int operand2, String op) {
        int result = 0;
        switch(op) {
            case "+":
                result = operand2 + operand1;
                break;
            case "-":
                result = operand2 - operand1;
                break;
            case "*":
                result = operand2 * operand1;
                break;
            case "/":
                result = (int) Math.floor((double) operand2 / (double) operand1);
                break;
            case "^":
                result = (int) Math.pow(operand2, operand1);
                break;
            default:
                result = 0;
        }
        return result;
    }
    
    /**
     * Using Enumeration Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || 
            s.equals("/") || s.equals("^");
    }
}
