class Solution {
    /**
     * Approach III : Using Optimal (Binary Search on Answers) Approach
     * 
     * TC: O(log(Max(a, b) - Min(a, b)) x (log(M) + log(N)))
     * SC: O(1)
     */
    public int kthElement(int a[], int b[], int k) {
        int m = a.length;
        int n = b.length;
        int low = Math.min(a[0], b[0]);
        int high = Math.max(a[m - 1], b[n - 1]);
        while (low < high) { // TC: O(log(Max(a, b) - Min(a, b)))
            int mid = low + (high - low) / 2;
            int countElementsLessInA = upperBound(a, m, mid); // TC: O(log(M))
            int countElementsLessInB = upperBound(b, n, mid); // TC: O(log(N))
            int totalCount = countElementsLessInA + countElementsLessInB;
            if (totalCount >= k) {
                // we need to eliminate right half
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    /**
     * Using Binary Search (To Find Upper Bound) Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int upperBound(int[] arr, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Approach II : Using Better (Max-Heap) Approach
     * 
     * TC: O(M x log(K)) + O(N x log(K)) ~ O((M + N) x log(K))
     * SC: O(K)
     */
    public int kthElementBetter(int a[], int b[], int k) {
        PriorityQueue<Integer> pq =
            new PriorityQueue<Integer>((p, q) -> q - p); // SC: O(K)
        for (int x : a) {        // TC: O(M)
            if (pq.size() < k) {
                pq.offer(x);     // TC: O(log(K))
            } else {
                if (x < pq.peek()) {
                    pq.poll();
                    pq.offer(x); // TC: O(log(K))
                }
            }
        }
        for (int x : b) {        // TC: O(N)
            if (pq.size() < k) {
                pq.offer(x);
            } else {
                if (x < pq.peek()) {
                    pq.poll();
                    pq.offer(x); // TC: O(log(K))
                }
            }
        }
        return pq.peek();
    }

    /**
     * Approach I : Using Brute-Force (Two Pointers + Extra Space) Approach
     * 
     * TC: O(M + N)
     * SC: O(M + N)
     */
    public int kthElementBruteForce(int a[], int b[], int k) {
        int m = a.length;
        int n = b.length;
        int[] merged = new int[m + n]; // SC: O(M + N)
        int p = 0; // pointer at the start of array 'a'
        int q = 0; // pointer at the start of array 'b'
        int r = 0; // pointer at the start of array 'merged'
        while (p < m && q < n) { // TC: O(M + N)
            if (a[p] < b[q]) {
                merged[r] = a[p];
                p++;
            } else {
                merged[r] = b[q];
                q++;
            }
            r++;
        }
        while (p < m) {
            merged[r] = a[p];
            p++;
            r++;
        }
        while (q < n) {
            merged[r] = b[q];
            q++;
            r++;
        }
        return merged[k - 1];
    }
}
