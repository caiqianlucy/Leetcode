/* author@ Qian Cai
 * Title@ Peeking Iterator
 * Given an Iterator class interface with methods: next() and hasNext(), design and 
 * implement a PeekingIterator that support the peek() operation -- it essentially 
 * peek() at the element that will be returned by the next call to next().
 * 
 */
import java.util.*;
public class LeetCode284 {
	class PeekingIterator implements Iterator<Integer> {
	    Iterator<Integer> iter;
	    Integer peekedValue = null;
		public PeekingIterator(Iterator<Integer> iterator) {
		    // initialize any member here.
	        iter = iterator;
		    
		}
		
	    // Returns the next element in the iteration without advancing the iterator.
		public Integer peek() {
	        if (peekedValue == null){
	            if (!iter.hasNext()) throw new NoSuchElementException();
	            peekedValue = iter.next();
	        }
	        return peekedValue;
		}
		
		// hasNext() and next() should behave the same as in the Iterator interface.
		// Override them if needed.
		@Override
		public Integer next() {
		    if (peekedValue != null){
	            Integer res = peekedValue;
	            peekedValue = null;
	            return res;
	        } 
	        if (!iter.hasNext()){
	            throw new NoSuchElementException();
	        }
	        return iter.next();
		}
		
		@Override
		public boolean hasNext() {
		    return peekedValue != null || iter.hasNext();
		}
	}
}
