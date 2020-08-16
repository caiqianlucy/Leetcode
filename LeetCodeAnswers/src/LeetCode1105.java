/* author@ Qian Cai
 * Title@ Filling Bookcase Shelves
 * We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].

We want to place these books in order onto bookcase shelves that have total width shelf_width.

We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width), then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down.  We repeat this process until there are no more books to place.

Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.  For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.

Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
time: O(m*width) space: O(m*width)
 */
import java.util.*;
public class LeetCode1105 {
	int m, w;
    int[][] books;
    int[][] memo;
    public int minHeightShelves(int[][] books, int shelf_width) {
        m = books.length;
        w = shelf_width;
        this.books = books;
        memo = new int[m][w+1]; //memo[i][j]: from index i books with width j left minHeight needed
        for (int[] arr: memo) Arrays.fill(arr, -1);
        return helper(0, 0, 0, memo);
    }
    public int helper(int idx, int height, int width, int[][] memo){
        if (idx == m) return height;
        if (memo[idx][width] != -1) return memo[idx][width];
        int res = Integer.MAX_VALUE;
        if (height == 0){
            res = helper(idx+1, books[idx][1], w-books[idx][0], memo);
        }
        else if (books[idx][0] > width){
            //can only put in next shelf
            res = height + helper(idx+1, books[idx][1], w-books[idx][0], memo);
        } else {
            res = Math.min(height + helper(idx+1, books[idx][1], w-books[idx][0], memo), helper(idx+1, Math.max(height, books[idx][1]), width-books[idx][0], memo));
        }
        memo[idx][width] = res;
        return res;
    }
}
