class Solution {
    /**
     * Approach : Using Sorting Approach
     * 
     * TC: O(N) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N)
     */
    public String findLargest(int[] arr) {
        int n = arr.length;
        String[] strArr = new String[n]; // SC: O(N)
        for (int i = 0; i < n; i++) {    // TC: O(N)
            strArr[i] = String.valueOf(arr[i]);
        }
        Arrays.sort(strArr, (a, b) -> {
            return (b + a).compareTo(a + b);
        }); // TC: O(N x log(N))
        // as strArr is already in descending order so if strArr[0] is "0" then return "0"
        if (strArr[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {    // TC: O(N)
            sb.append(strArr[i]);
        }
        return sb.toString();
    }
}
