/* author@ Qian Cai
 * Title@ Strong Password Checker
 * A password is considered strong if below conditions are all met:

It has at least 6 characters and at most 20 characters.
It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make s a strong password. If s is already strong, return 0.

Insertion, deletion or replace of any one character are all considered as one change.
 */
import java.util.*;
public class LeetCode420 {
	 public int strongPasswordChecker(String s) {
	        int LEN_LOWER_LIMIT = 6, LEN_HIGHER_LIMIT = 20;
	        int len = s.length();
	        int has_lower= 0, has_upper = 0, has_digit = 0;
	        List<Integer> repeats = new LinkedList();
	        int i = 0;
	        while (i < len){
	            char c = s.charAt(i);
	            if (Character.isUpperCase(c)) has_upper = 1;
	            if (Character.isLowerCase(c)) has_lower = 1;
	            if (Character.isDigit(c)) has_digit = 1;
	            int j = i+1;
	            while (j < len && s.charAt(i) == s.charAt(j)) j++;
	            if (j - i >= 3) repeats.add(j-i);
	            i = j;
	        }
	        int cnt_missing = 3 - has_lower -has_upper - has_digit;
	        Collections.sort(repeats,(a, b)->(a%3 -b%3));
	        Deque<Integer> repeats_d = new LinkedList();
	        for (int r: repeats) repeats_d.addLast(r);
	        int res = 0;
	        while (!repeats_d.isEmpty()){
	            int r = repeats_d.removeFirst();
	            if (len < LEN_LOWER_LIMIT){
	                res += r/3; //insert
	                len += r/3;
	                cnt_missing -= r/3;
	            } else if (len > LEN_HIGHER_LIMIT){
	                int new_del = 0;
	                if (r % 3 == 0) new_del = 1;
	                else if (r % 3 == 1) new_del = Math.min(2, len-LEN_HIGHER_LIMIT);
	                else new_del = Math.min(r-2, len-LEN_HIGHER_LIMIT);
	                res += new_del;
	                len -= new_del;
	                r -= new_del;
	                if (r > 2) repeats_d.addLast(r);
	            } else {
	                int new_replace = r/3;
	                res += new_replace;
	                cnt_missing -= new_replace;
	            }
	        }
	        cnt_missing = Math.max(cnt_missing, 0);
	        if (len < LEN_LOWER_LIMIT){
	            len += cnt_missing;
	            res += cnt_missing;
	        } else {
	            res += cnt_missing;
	        }
	        res += Math.max(0, LEN_LOWER_LIMIT-len) + Math.max(0, len-LEN_HIGHER_LIMIT);
	        return res;
	    }
}
