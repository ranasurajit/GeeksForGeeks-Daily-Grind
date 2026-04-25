class Solution {
    /**
     * Approach : Using Three Pointers Approach
     * 
     * TC : O(p + q + r) ~ O(n)
     * SC : O(1)
     */
    public ArrayList<Integer> commonElements(int[] a, int[] b, int[] c) {
        int p = a.length;
        int q = b.length;
        int r = c.length;
        int i = 0; // pointer at the start of array 'a'
        int j = 0; // pointer at the start of array 'b'
        int k = 0; // pointer at the start of array 'c'
        ArrayList<Integer> result = new ArrayList<>();
        while (i < p && j < q && k < r) { // TC : O(p + q + r)
            if (a[i] == b[j] && b[j] == c[k]) {
                // don't add duplicates to result
                if (result.isEmpty() || result.get(result.size() - 1) != a[i]) {
                    result.add(a[i]);
                }
                i++;
                j++;
                k++;
            } else if (a[i] < b[j]) {
                // a[i] is the smallest
                i++;
            } else if (b[j] < c[k]) {
                // b[j] is the smallest
                j++;
            } else {
                // c[k] is the smallest
                k++;
            }
        }
        return result;
    }
}
