/* author@ Qian Cai
 * Title@ Valid Number
 * Time: O(n)
 */
public class LeetCode65 {
	 /*
    FSA (finite state automata)
state: 
	0: empty or space
	1: number without dot
	2: (space) + dot
	3: number with one dot
	4: (spaces) + sign
	5: "1e"
    6: "1e+"
	7: 1e+1
  	8: number + (space)
	-1
input type:
	0: space
	1: digit
	2: dot
	3: sign
	4: exp
	5: invalid
    
    */
    public boolean isNumber(String s) {
        int[][] transitions = new int[][]{
			{0, 1,  2,  4,  -1},       //0: empty or space
			{8, 1,  3,  -1,  5},       //1: number without dot
	        {-1,  3, -1, -1, -1},     //2: (space) + dot
	        {8,  3, -1,  -1,  5},     //3: number with one dot
	        {-1, 1,  2,  -1, -1},    // 4: (spaces) + sign
	        {-1, 7,  -1,  6,  -1},  //5: "1e"
            {-1, 7,  -1, -1,  -1},    //6: "1e+"
	        { 8,  7, -1, -1,  -1},   //7: 1e+1
  	        {8, -1, -1, -1, -1}      //8: number + (space)
			};
			int state = 0;
			int input = -1;
			for (char c: s.toCharArray()){
				if (c == ' ') input = 0;
				else if (Character.isDigit(c)) input = 1;
				else if (c == '.') input = 2;
				else if (c == '+' || c == '-') input = 3;
				else if (c == 'e') input = 4;
				else return false;
				state = transitions[state][input];
                if (state == -1) return false;
			}
			return state == 1 || state == 3 || state == 7 || state == 8;
    }
}
