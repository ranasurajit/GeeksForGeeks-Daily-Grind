class Solution {
    /**
     * Approach : Using Hashing Approach
     * 
     * TC: O(n²) + O(n) ~ O(n²)
     * SC: O(n)
     */
    static ArrayList<Integer> diagView(int mat[][]) {
        int n = mat.length;
        Map<Integer, ArrayList<Integer>> map =
            new HashMap<>(); // SC: O(2 x n) ~ O(n)
        for (int i = 0; i < n; i++) {     // TC: O(n)
            for (int j = 0; j < n; j++) { // TC: O(n)
                int key = i + j;
                map.computeIfAbsent(key,
                    k -> new ArrayList<>()).add(mat[i][j]);
            }
        }
        ArrayList<Integer> view = new ArrayList<>();
        for (int key = 0; key < 2 * n - 1; key++) { // TC: O(2 x n) ~ O(n)
            view.addAll(map.get(key));
        }
        return view;
    }
}
