/* author@Qian Cai
 * Title@ Minimize Max Distance to Gas Station
 * On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.

Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.

Return the smallest possible value of D.

Example:

Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000
Note:

stations.length will be an integer in range [10, 2000].
stations[i] will be an integer in range [0, 10^8].
K will be an integer in range [1, 10^6].
Answers within 10^-6 of the true value will be accepted as correct.
 * 
 */
public class LeetCode774 {
	//time: O(nlogM) n is length of stations, M is the range of possible answers (hi)*10^6(precision)
    public double minmaxGasDist(int[] stations, int K) {
        double lo = 0.0;
        double hi = (stations[1] - stations[0])*1.0;
        for (int i = 2; i < stations.length; i++){
            hi = Math.max(hi, stations[i]- stations[i-1]);
        }
        while (Math.abs(lo-hi) > 0.000001){
            double mi = (hi-lo)/2.0 + lo;
            if (count(stations, mi) > K) lo = mi;
            else hi = mi;
        }
        return lo;
    }
    /* [1, 10]
    if minimum distance is 3.0
    need to add 3.99999, 6.99998, 9.99997 three stations
    [1, 11]
    need to add 4, 7, 10
    
    station number to make minimum stations dis less than distance
    */
    public int count(int[] stations, double distance){
        int res = 0;
        for (int i = 1; i < stations.length; i++){
            res += (int) ((stations[i] - stations[i-1])/distance);
        }
        return res;
    }
}
