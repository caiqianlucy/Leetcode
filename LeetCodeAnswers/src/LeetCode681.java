/* author@ Qian Cai
 * title@ Next Closest Time
 * Given a time represented in the format "HH:MM", form the next closest time 
 * by reusing the current digits. There is no limit on how many times a digit 
 * can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09"
 are all valid. "1:34", "12:9" are all invalid.


 * 
 */
import java.util.*;
public class LeetCode681 {
	public String nextClosestTime(String time) {
        Set<Character> set = new HashSet();
        for (int i = 0; i < 5; i++){
            if (i != 2){
                set.add(time.charAt(i));
            }
        }
        int cur = Integer.valueOf(time.substring(0, 2))*60 + Integer.valueOf(time.substring(3, 5));
        String res = time;
        int delta = Integer.MAX_VALUE;
        for (char a: set){
            for (char b: set){
                int h = (a-'0')*10 + (b-'0');
                if (h <= 24){
                    for (char c: set){
                        for (char d: set){
                            int m = (c-'0')*10 + (d-'0');
                            if ( m <= 59){
                                if (h*60 + m <= 24*60){
                                    int newDelta = (1440+h*60+m -cur)%1440;
                                    if (newDelta != 0 && newDelta < delta){
                                        res = a +"" + b + ":" + c + "" + d;
                                        delta = newDelta;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}
