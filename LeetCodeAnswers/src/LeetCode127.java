/*author@ Qian Cai
 * Title@ Word Ladder
 * Given two words (beginWord and endWord), and a dictionary's word list, 
 * find the length of shortest transformation sequence from beginWord to 
 * endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
 * ============================================================================
 * time, space O(mn), m is the length of each word, n is the length of wordList
 * ============================================================================
 */
import java.util.*;
public class LeetCode127 {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet(wordList);
        if (!wordSet.contains(endWord)) return 0;
        Map<String, List<String>> map = new HashMap();
        for (String s: wordList){
            for (int i = 0; i < s.length(); i++){
                String key = s.substring(0, i) + '*' + s.substring(i+1);
                if (!map.containsKey(key)) map.put(key, new ArrayList());
                map.get(key).add(s);
            }
        }
        int level = 1;
        Set<String> visited = new HashSet();
        Queue<String> queue = new LinkedList();
        queue.add(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                String cur = queue.poll();
                if (cur.equals(endWord)) return level;
                dfs(cur, map, queue, visited);
            }
            level++;
        }
        return 0;
    }
    public void dfs(String cur, Map<String, List<String>> map, Queue<String> queue, Set<String> visited){
        for (int i = 0; i < cur.length(); i++){
            String key = cur.substring(0, i) + '*' + cur.substring(i+1);
            if (!map.containsKey(key)) continue;
            for (String next: map.get(key)){
                if (!next.equals(cur) && !visited.contains(next)){
                    queue.add(next);
                    visited.add(next);
                }
            }
        }
    }
}
