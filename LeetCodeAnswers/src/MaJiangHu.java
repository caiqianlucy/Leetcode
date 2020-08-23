/* majiang Win condition:
 * 
 * 1 pair,
 * rest can form triple
 * or straight of 3 
 * 11 123 222 is win
 * 
 * Given array of numbers from 1-9, determine whether can win?;
 * time: O(n)
 * 
 */
public class MaJiangHu {
	public static boolean canWin(int[] arr) {
		int[] count = new int[10];
		for (int i = 0; i < arr.length; i++){
			count[arr[i]]++;
		}
		for (int i = 1; i < 10; i++) {
			if (count[i] >= 2) {
				count[i] -= 2;
				if (helper(count)) return true;
				count[i] += 2;
			}
		}
		return false;
	}
	public static boolean helper(int[] count) {
		int[] temp = count.clone();
		for (int i = 1; i <= 7; i++) {
			int str = count[i]%3;
			temp[i] -= str;
			if (count[i+1] < str || count[i+2] < str) return false;
			temp[i+1] -= str;
			temp[i+2] -= str;
		}
		return temp[8] % 3 == 0 && temp[9] % 3 == 0;
	}
	public static void main(String[] args) {
		int[] arr = new int[] {1, 1, 2, 3, 2, 2, 2};
		System.out.println(canWin(arr));
	}
}
