/* author@ Qian Cai
 * Title@ RLE Iterator
 * Write an iterator that iterates through a run-length encoded sequence.

The iterator is initialized by RLEIterator(int[] A), where A is a run-length encoding of some sequence.  More specifically, for all even i, A[i] tells us the number of times that the non-negative integer value A[i+1] is repeated in the sequence.

The iterator supports one function: next(int n), which exhausts the next n elements (n >= 1) and returns the last element exhausted in this way.  If there is no element left to exhaust, next returns -1 instead.

For example, we start with A = [3,8,0,9,2,5], which is a run-length encoding of the sequence [8,8,8,5,5].  This is because the sequence can be read as "three eights, zero nines, two fives".
 */
public class LeetCode900 {
	class RLEIterator {
	    int[] A;
	    int i;
	    public RLEIterator(int[] A) {
	        this.A = A;
	        i = 0;
	    }
	    
	    public int next(int n) {
	        //System.out.println(" n is " + n);
	        while (n > 0 && i < A.length){          
	            if (A[i] < n){ 
	                
	                n -= A[i]; 
	                A[i] = 0;
	                i += 2;                  
	            } else if (A[i] == n){                
	                  A[i] = 0;
	                  i += 2;
	                  //System.out.println(A[i-1]);
	                  return A[i-1];             
	            }  else {     
	         
	                A[i] -= n;
	                n = 0;
	                return A[i+1];
	            }
	        }
	        return -1;
	    }
	}
}
