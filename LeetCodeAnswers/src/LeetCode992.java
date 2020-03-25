
public class LeetCode992 {
	public int subarraysWithKDistinct(int[] A, int K) {
        return subarraysWithAtMostKDistinct(A, K) - subarraysWithAtMostKDistinct(A, K-1);
    }
    private int subarraysWithAtMostKDistinct(int[] A, int K){
        if (K == 0) return 0;
        int i = 0, j = 0, cnt = 0, res = 0;
        int[] counts = new int[A.length+1];
        while (j < A.length){
             if (counts[A[j++]]++ == 0) cnt++;
             while (cnt > K){
                 if (--counts[A[i++]] == 0) cnt--;
             }
            res += j-i;
        }
        return res;
    }
}
