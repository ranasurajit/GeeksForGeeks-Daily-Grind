class Solution {
    /**
     * Approach : Using Simulation + Math Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public ArrayList<Integer> constructArr(int[] arr) {
        int m = arr.length;
        ArrayList<Integer> answer = new ArrayList<Integer>();
        if (m == 1) {
            answer.add(1);
            answer.add(arr[0] - 1);
            return answer;
        }
        /**
         * n * (n - 1) = 2 * m;
         * i.e. n ^ 2 - n - 2 * m = 0
         * 
         * so n to be an integer, 
         * (-1)^2 - 4 * 1 * (-2 * m) i.e. (1 + 8M) 
         * should be a perfect square
         * 
         * For a quadratic equation: a.x^2 + b.x + c = 0, 
         * n = (-b +- Math.sqrt(b^2 - 4.a.c)) / (2.a)
         */
        int n = (1 + (int) Math.sqrt(1 + (8 * m))) / 2;
        int[] res = new int[n]; // SC: O(N)
        /**
         * res[0] + res[1] = arr[0]
         * res[0] + res[2] = arr[1]
         * res[1] + res[2] = arr[2]
         * 
         * i.e
         * 2 * res[0] + (res[1] + res[2]) = (arr[0] + arr[1])
         * 2 * res[0] + arr[2] = (arr[0] + arr[1])
         * res[0] = ((arr[0] + arr[1]) - arr[2]) /2
         */
        res[0] = (arr[0] + arr[1] - arr[n - 1]) / 2;
        for (int i = 1; i < n; i++) { // TC: O(N)
            res[i] = arr[i - 1] - res[0];
        }
        for (int x : res) { // TC: O(N)
            answer.add(x);
        }
        return answer;
    }
}
