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

    /**
     * Using Stack + String Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public void append(char x) {
        undoSt.push(x); // push to undo stack
        redoSt.clear(); // clear history
        sb.append(x);
    }

    /**
     * Using Stack + String Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public void undo() {
        if (undoSt.isEmpty()) {
            return;
        }
        char delChar = undoSt.pop();
        sb.deleteCharAt(sb.length() - 1);
        redoSt.push(delChar);
    }

    /**
     * Using Stack + String Simulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public void redo() {
        if (redoSt.isEmpty()) {
            return;
        }
        char redoChar = redoSt.pop();
        sb.append(redoChar);
        undoSt.push(redoChar);
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
