/* author@Qian Cai
 * Title@Open the lock
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 * 
 */
import java.util.*;
public class LeetCode752 {
public int openLock(String[] deadends, String target) {
        
        Set<String> dead = new HashSet();
        for (String d: deadends) dead.add(d);
        if (dead.contains("0000")) return -1;
        Queue<String> queue = new LinkedList();
        queue.offer("0000");
        Set<String> visited = new HashSet();
        visited.add("0000");
        int res = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                String node = queue.poll();
                if (node.equals(target)) return res;
                for (int j = 0; j < 4; j++){
                    for (int d = -1; d <= 1; d+=2){
                        int next = ((node.charAt(j)-'0') + d + 10) % 10;
                        String nei = node.substring(0, j) + ("" + next) + node.substring(j+1);
                        //System.out.println(nei);
                        if (!visited.contains(nei) && !dead.contains(nei)){
                            visited.add(nei);
                            queue.offer(nei);
                        }
                    }
                }
            }
            res++;
        }
        return -1;
    }
}
