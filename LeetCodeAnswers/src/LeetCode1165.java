/* author@ Qian Cai
 * Title@ Single-Row Keyboard
 * There is a special keyboard with all keys in a single row.

Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25), initially your finger is at index 0. To type a character, you have to move your finger to the index of the desired character. The time taken to move your finger from index i to index j is |i - j|.

You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.
 * 
 */
public class LeetCode1165 {
	public int calculateTime(String keyboard, String word) {
        int[] dicts = new int[26];
        int idx = 0;
        for (char c: keyboard.toCharArray()){
            dicts[c-'a'] = idx++;
        }
        int prev = 0;
        int res = 0; 
        for (int i = 0; i < word.length(); i++){
            int cur = word.charAt(i) - 'a';
            int j = dicts[cur];
            res += Math.abs(j-prev);
            prev = j;
        }
        return res;
    }
}
