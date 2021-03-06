import java.util.*;

/*author@ Qian Cai
 * Title@ Design A Leaderboard
 * Design a Leaderboard class, which has 3 functions:

addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
top(K): Return the score sum of the top K players.
reset(playerId): Reset the score of the player with the given id to 0. It is guaranteed that the player was added to the leaderboard before calling this function.
Initially, the leaderboard is empty.

 * HashMap + PriorityQueue
 */
class LeetCode1244Leaderboard {
    Map<Integer, Integer> map;
    PriorityQueue<Integer> pq;
    //Space: O(n)
    public LeetCode1244Leaderboard() {
        map = new HashMap();
        pq = new PriorityQueue<Integer>((a, b)->(map.get(b)-map.get(a)));
    }
    //Time: O(logn)
    public void addScore(int playerId, int score) {
        if (!map.containsKey(playerId)){
            map.put(playerId, 0);
        } else {
            pq.remove(playerId);
        }
        map.put(playerId, map.get(playerId)+score);
        pq.offer(playerId);
        
    }
    //Time, Space: O(Klogn)
    public int top(int K) {
        List<Integer> list = new ArrayList();
        int res = 0;
        while (--K >= 0 && !pq.isEmpty()){
            int cur = pq.poll();
            res += map.get(cur);
            list.add(cur);
        }
        for (int n: list) pq.add(n);
        return res;
    }
    //Time: O(logn)
    public void reset(int playerId) {
        if (map.containsKey(playerId)){
            pq.remove(playerId);
        }
        map.put(playerId, 0);
        pq.offer(playerId);
        
    }
    //Solution2: HashMap + treeMap
    class Leaderboard {
        Map<Integer, Integer> idToScore;
        TreeMap<Integer, Integer> tm; //score to counts of the score
        public Leaderboard() {
            tm = new TreeMap();
            idToScore = new HashMap();
        }
        
        public void addScore(int playerId, int score) {
            int oldScore = idToScore.getOrDefault(playerId, 0);
            if (oldScore != 0){
                tm.put(oldScore, tm.get(oldScore)-1);
                 if (tm.get(oldScore) == 0) tm.remove(oldScore);
            } 
           
            int newScore = oldScore + score;
            idToScore.put(playerId, newScore);
            tm.put(newScore, tm.getOrDefault(newScore, 0) + 1);
        }
        
        public int top(int K) {
            int res = 0;
            Integer max = tm.lastKey();
            while (K >0 && max != null){
                int score = tm.floorKey(max);
                int count = tm.get(score);
                int c = Math.min(count, K);
                res += c*score;
                K-= c;
                max = tm.lowerKey(score);
            }
            return res;
        }
        
        public void reset(int playerId) {
            int oldScore = idToScore.getOrDefault(playerId, 0);
            if (oldScore != 0){
                tm.put(oldScore, tm.get(oldScore)-1);
                if (tm.get(oldScore) == 0) tm.remove(oldScore);
            }
            idToScore.remove(playerId);
        }
    }

}
