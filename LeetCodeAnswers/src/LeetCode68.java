/* author@ Qian Cai
 * title@ Text Justification
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
 * time, space: O(n)
 */
import java.util.*;
public class LeetCode68 {
	public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList();
        int i = 0;
        int cur = 0;
        while (i < words.length){
            cur = words[i].length();
            int j = i;           
            while (j + 1 < words.length && cur + words[j+1].length() < maxWidth){
                cur += words[++j].length()+1;
            }
            if (j < words.length-1) ans.add(justify(words, i, j, cur, maxWidth));
            else ans.add(leftJustify(words, i, j, cur, maxWidth));
            i = j+1;
        }
        return ans;
    }
    public String justify(String[] words, int i, int j, int width, int maxWidth){
        if (i == j){
            return leftJustify(words, i, j, width, maxWidth);
        }
        int space = (maxWidth-width)/(j-i) + 1;
        int extra =(maxWidth-width)%(j-i);
        StringBuilder sb = new StringBuilder();
        for (int k = i; k <= j-1; k++){
            sb.append(words[k]);
            for (int c = 0; c < space; c++) sb.append(" ");
            if (k-i < extra) sb.append(" ");
        }
        sb.append(words[j]);
        return sb.toString();
    }
    public String leftJustify(String[] words, int i, int j, int width, int maxWidth){
        StringBuilder sb = new StringBuilder();
        if (i == j) sb.append(words[i]);
        else {
            for (int k = i; k < j; k++){
                sb.append(words[k]);
                sb.append(" ");
            }
            sb.append(words[j]);
        }
        for (int k = 0; k < maxWidth-width; k++){
            sb.append(" ");
        }
        return sb.toString();
    }
}
