class kQueues {
    private int n;
    private int k;
    private int[][] arr;
    private Set<Integer> freeIndices;
    private int[] head;
    private int[] tail;

    /**
     * Approach : Using Queue + Pointers Approach
     * 
     * TC: O(N)
     * SC: O(N + K)
     */
    kQueues(int n, int k) {
        this.n = n;
        this.k = k;
        this.arr = new int[n][2]; // SC: O(N) -  we will store { current, next } indices
        this.freeIndices = new HashSet<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            freeIndices.add(i);
        }
        // since there are k queues so each has corresponding LinkedList with head and tail
        this.head = new int[k]; // SC: O(K)
        this.tail = new int[k]; // SC: O(K)
        Arrays.fill(head, -1);
        Arrays.fill(tail, -1);
    }

    /**
     * Using Queue + Pointers Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    void enqueue(int x, int i) {
        if (isFull()) {
            return;
        }
        int idx = freeIndices.iterator().next();
        freeIndices.remove(idx);
        arr[idx][0] = x;
        arr[idx][1] = -1;
        if (head[i] == -1) {
            // // ith queue is empty
            head[i] = idx;
            tail[i] = idx;
        } else {
            // ith queue has some elements
            // update prev's tails next to idx
            arr[tail[i]][1] = idx;
            // now update the new tail for same ith queue
            tail[i] = idx;
        }
    }

    /**
     * Using Queue + Pointers Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    int dequeue(int i) {
        if (isEmpty(i)) {
            return -1;
        }
        int idx = head[i];
        freeIndices.add(idx);
        head[i] = arr[idx][1];
        return arr[idx][0];
    }

    /**
     * Using Queue + Pointers Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    boolean isEmpty(int i) {
        return head[i] == -1;
    }

    /**
     * Using Queue + Pointers Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    boolean isFull() {
        return freeIndices.isEmpty();
    }
}
