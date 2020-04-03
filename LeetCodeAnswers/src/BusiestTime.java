/*author@ Qian Cai
 * Title: Busiest Time in the Mall from pramp practice
 * The Westfield Mall management is trying to figure out what the busiest moment at the mall was last year. You’re given data extracted from the mall’s door detectors. Each data point is represented as an integer array whose size is 3. The values at indices 0, 1 and 2 are the timestamp, the count of visitors, and whether the visitors entered or exited the mall (0 for exit and 1 for entrance), respectively. Here’s an example of a data point: [ 1440084737, 4, 0 ].

Note that time is given in a Unix format called Epoch, which is a nonnegative integer holding the number of seconds that have elapsed since 00:00:00 UTC, Thursday, 1 January 1970.

Given an array, data, of data points, write a function findBusiestPeriod that returns the time at which the mall reached its busiest moment last year. The return value is the timestamp, e.g. 1480640292. Note that if there is more than one period with the same visitor peak, return the earliest one.

Assume that the array data is sorted in an ascending order by the timestamp. Explain your solution and analyze its time and space complexities.
 * Time: O(n), Space: O(1)
 */
import java.util.*;

public class BusiestTime {
	static int findBusiestPeriod(int[][] data) {
	    // your code goes here
	    int n = data.length;
	    int maxCount = 0;
	    int cur = 0, i = 0, res = 0;
	    while (i < n){
	      int time = data[i][0];
	      if (data[i][2] == 1) cur += data[i][1];
	      else  cur -= data[i][1];
	      while (i+1 < n && time == data[i+1][0]){
	         i++;
	         if (data[i][2] == 1) cur += data[i][1];
	         else  cur -= data[i][1];
	      } 
	      if (cur > maxCount){
	        maxCount = cur;
	        res = time;
	      }
	      i++;
	    }
	    return res;
	  }

	  public static void main(String[] args) {
	     int[][] data = {{1487799425, 14, 1}, {1487799425, 4,  0}, {1487799425, 2,  0}, {1487800378, 10, 1}, {1487801478, 18, 0}, {1487801478, 18, 1}, {1487901013, 1,  0}, {1487901013, 1,  0}, {1487901013, 1,  0}, {1487901211, 7,  1}, {1487901211, 7,  0}}; 
	                 
	     System.out.println(findBusiestPeriod(data));         
	                
	  
	  }
}
