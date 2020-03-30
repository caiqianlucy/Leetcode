/*author@Qian Cai
Title#Airplane Seat Assignment Probability
n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and picks a seat randomly. But after that, the rest of passengers will:

Take their own seat if it is still available, 
Pick other seats randomly when they find their seat occupied 
What is the probability that the n-th person can get his own seat?
Two solution:
1. dp solution: Time: O(n) Space: O(1)
Two situation that the n the person can take his seat:
a. first person take his seat 1/n
b. first person take the rest seat except his own and last person's seat, n-2/n and rest n-1 is equal to f(n-1) question
Therefore f(n) = 1/n + f(n-1)*(n-2)/n
2. Since when n == 2, f(n) = 0.5,put that into equation, f(i) = 0.5 when i > 2;  Time, space: O(1)
*/
public class LeetCode1227 {
	public double nthPersonGetsNthSeat1(int n) {
        if (n == 1) return 1.0;
        return 1.0/n + (n-2)*nthPersonGetsNthSeat(n-1)/n;        
    }
    public double nthPersonGetsNthSeat(int n) {
        
        return n >= 2? 0.5 : 1.0;        
    }
}
