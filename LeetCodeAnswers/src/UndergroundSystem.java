import java.util.*;

/*author@Qian Cai
 * title@Design Underground System
 * Implement the class UndergroundSystem that supports three methods:

1. checkIn(int id, string stationName, int t)

A customer with id card equal to id, gets in the station stationName at time t.
A customer can only be checked into one place at a time.
2. checkOut(int id, string stationName, int t)

A customer with id card equal to id, gets out from the station stationName at time t.
3. getAverageTime(string startStation, string endStation) 

Returns the average time to travel between the startStation and the endStation.
The average time is computed from all the previous traveling from startStation to endStation that happened directly.
Call to getAverageTime is always valid.
You can assume all calls to checkIn and checkOut methods are consistent. That is, if a customer gets in at time t1 at some station, then it gets out at time t2 with t2 > t1. All events happen in chronological order.
 * 
 */
public class UndergroundSystem {
	Map<Integer, Info> timeMap;
    Map<String, Map<String, Pair>> path;    
    public UndergroundSystem() {
        timeMap = new HashMap();
        path = new HashMap();
    }    
    public void checkIn(int id, String stationName, int t) {
        Info info = new Info(t, stationName);
        timeMap.put(id, info);       
    }    
    public void checkOut(int id, String stationName, int t) {
        Info info = timeMap.get(id);
        String entry = info.entry;
        int last = info.t;
        if (!path.containsKey(entry)){
            Map<String, Pair> out = new HashMap();
            out.put(stationName, new Pair(t-last, 1));
            path.put(entry, out);
        } else {
            Map<String, Pair> out = path.get(entry);
            if (!out.containsKey(stationName)) out.put(stationName, new Pair(t-last, 1));
            else {
                Pair previous = path.get(entry).get(stationName);
                Pair cur = new Pair(previous.sum + t - last, previous.size+1);
                out.put(stationName, cur);
            }
            path.put(entry, out);
        }
        timeMap.remove(id);
    }
        public double getAverageTime(String startStation, String endStation) {
        Pair pair = path.get(startStation).get(endStation);
        return (double)pair.sum/(double)pair.size;
    }
    private class Pair{
        int sum, size;
        private Pair(int sum, int size){
             this.sum = sum;
             this.size = size;
        }
    }
    private class Info{
        int t;
        String entry;
        private Info(int time, String e){
            t = time;
            entry = e;
        }       
    }
}
