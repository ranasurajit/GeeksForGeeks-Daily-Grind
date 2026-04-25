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
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < p && j < q && k < r) { // TC : O(p + q + r)
            if (a[i] == b[j] && b[j] == c[k]) {
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
