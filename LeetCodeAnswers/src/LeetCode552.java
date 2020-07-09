/* author@ Qian Cai
 * Title@ Student Attendance Record II
 * Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 * 
 */
public class LeetCode552 {
	public int checkRecord(int n) {
        long[] f = new long[n <= 5? 6: n+1];
		long M = 1000000007;
		f[0] = 1;
        f[1] = 2;
        f[2] = 4;
        f[3] = 7;
		for (int i = 4; i <= n; i++){
			f[i] = (2*f[i-1] + M - f[i-4])%M;
	 	}
		long res = f[n];
		for (int i = 0; i < n; i++){
			res += (f[i]*f[n-1-i])%M;
			res %= M;
		}
		return (int) (res%M);
    }
}
