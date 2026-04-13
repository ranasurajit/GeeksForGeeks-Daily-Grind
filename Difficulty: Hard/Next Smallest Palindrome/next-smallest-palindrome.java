class Solution {
    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC: O(n)
     * SC: O(1)
     */
    static int[] nextPalindrome(int[] num) {
        int n = num.length;
        int targetIdx =  n / 2;
        int p = targetIdx - 1;
        int q = (n & 1) == 0 ? targetIdx : targetIdx + 1;
        boolean incrementNeeded = false;
        while (p >= 0 && q < n) { // TC: O(n)
            if (num[p] < num[q]) {
                incrementNeeded = true;
                break;
            } else if (num[p] > num[q]) {
                incrementNeeded = false;
                break;
            }
            p--;
            q++;
        }
        if (p < 0) {
            // num is already palindrome so we need to increment
            incrementNeeded = true;
        }
        p = targetIdx - 1;
        q = (n & 1) == 0 ? targetIdx : targetIdx + 1;
        while (p >= 0) {
            num[q++] = num[p--];
        }
        if (incrementNeeded) {
            int carry = 1;
            int i = (n & 1) == 0 ? targetIdx - 1 : targetIdx;
            while (i >= 0 && carry > 0) {
                int sum = carry + num[i];
                num[i] = sum % 10;
                carry = sum / 10;
                i--;
            }
            p = targetIdx - 1;
            q = (n & 1) == 0 ? targetIdx : targetIdx + 1;
            while (p >= 0) {
                num[q++] = num[p--];
            }
            if (carry > 0) {
                // if num is 9999
                int[] result = new int[n + 1];
                result[0] = 1;
                result[n] = 1;
                return result;
            }
        }
        return num;
    }
}
