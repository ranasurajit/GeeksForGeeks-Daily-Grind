/**
 * Using Stack + String Simulation Approach
 * 
 * TC: O(N)
 * SC: O(N) + O(N) + O(N)
 * 
 * where N is the total operations executed
 */
class Solution {
    private StringBuilder sb = new StringBuilder(); // SC: O(N)
    private Stack<Character> undoSt = new Stack<Character>(); // SC: O(N)
    private Stack<Character> redoSt = new Stack<Character>(); // SC: O(N)
    private int n = 0;

    /**
     * Using Stack + String Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public void append(char x) {
        redoSt.push(x);
        sb.append(x);
        n++;
    }

    /**
     * Using Stack + String Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public void undo() {
        char delChar = sb.charAt(n - 1);
        sb.deleteCharAt(n - 1);
        undoSt.push(delChar);
        n--;
    }

    /**
     * Using Stack + String Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public void redo() {
        if (undoSt.isEmpty()) {
            return;
        }
        char redoChar = undoSt.pop();
        sb.append(redoChar);
        redoSt.push(redoChar);
        n++;
    }

    /**
     * Using Stack + String Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public String read() {
        return sb.toString();
    }
}
