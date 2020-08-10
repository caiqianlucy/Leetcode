/* author@ Qian Cai
 * Title@ Bulls and Cows
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.
 * time: O(n) space: O(1)
 */
public class LeetCode299 {
	public String getHint(String secret, String guess) {
        int[] s_digits = new int[10];
        int[] g_digits = new int[10];
        int bull = 0, cow = 0;
        for (int i = 0; i < secret.length(); i++){
            char s = secret.charAt(i), g = guess.charAt(i);
            if (s == g) bull++;
            s_digits[s-'0']++;
            g_digits[g-'0']++;
        } 
        for (int i = 0; i < 10; i++){
            cow += Math.min(s_digits[i], g_digits[i]);
        }
        cow -= bull;
        return bull + "A" + cow + "B";
    }
}
