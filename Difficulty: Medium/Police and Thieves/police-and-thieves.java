class Solution {
    /**
     * Approach : Using Queue Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N1) + O(N2) ~ O(N)
     */
    public int catchThieves(char[] arr, int k) {
        int n = arr.length;
        // we will be adding the indices of Police and Thieves into Queues
        Queue<Integer> policeQueue = new LinkedList<Integer>(); // SC: O(N1)
        Queue<Integer> thiefQueue = new LinkedList<Integer>(); // SC: O(N2)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (arr[i] == 'P') {
                policeQueue.offer(i);
            } else {
                thiefQueue.offer(i);
            }
        }
        int caught = 0;
        while (!thiefQueue.isEmpty() && !policeQueue.isEmpty()) { // TC: O(N)
            int policeIndex = policeQueue.peek();
            int thiefIndex = thiefQueue.peek();
            if (Math.abs(policeIndex - thiefIndex) <= k) {
                caught++;
                // eject police and thief as 1 police can catcj 1 thief only
                policeQueue.poll();
                thiefQueue.poll();
            } else if (policeIndex < thiefIndex) {
                // police cannot catch anyone
                policeQueue.poll();
            } else {
                // thief cannot be catched
                thiefQueue.poll();
            }
        }
        return caught;
    }
}
