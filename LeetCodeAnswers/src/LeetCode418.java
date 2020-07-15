/* author@ Qian Cai
 * Title@ Sentence Screen Fitting
 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
 * 
 */
public class LeetCode418 {
	public int wordsTyping(String[] sentence, int rows, int cols) {
        
	      int[] nextIndex = new int[sentence.length];
	        int[] times = new int[sentence.length]; //how many times the sentence ends for start at sentence[i] at the new row
	        for(int i=0;i<sentence.length;i++) {
	            int curLen = 0;
	            int cur = i;
	            int time = 0;
	            while(curLen + sentence[cur].length() <= cols) {
	                curLen += sentence[cur++].length()+1;
	                if(cur==sentence.length) {
	                    cur = 0;
	                    time ++;
	                }
	            }
	            nextIndex[i] = cur;
	            times[i] = time;
	        }
	        int ans = 0;
	        int cur = 0;
	        for(int i=0; i<rows; i++) {
	            ans += times[cur];
	            cur = nextIndex[cur];
	        }
	        return ans;  
	    }
}
