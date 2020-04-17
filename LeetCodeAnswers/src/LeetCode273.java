/* author@ Qian Cai
 * Title@ Integer to English Words
 * Convert a non-negative integer to its english words representation. 
 * Given input is guaranteed to be less than 231 - 1.
 * 
 */
public class LeetCode273 {
	int T = 1000, M =1000000, B = 1000000000;
    public String numberToWords(int num) {
        String[] oneTo19 = "Zero One Two Three Four Five Six Seven Eight Nine Ten Eleven Twelve Thirteen Fourteen Fifteen Sixteen Seventeen Eighteen Nineteen".split(" ");
        String[] tens = "Twenty Thirty Forty Fifty Sixty Seventy Eighty Ninety".split(" ");
        if (num == 0) return "Zero";
        if (num < 20) return oneTo19[num];
        if (num < 100) return tens[(num/10)-2] + (num%10 == 0 ? "" : (" " + oneTo19[num%10]));
        if (num < T) return oneTo19[num/100] + " Hundred" + (num%100 == 0 ? "": (" "+ numberToWords(num%100)));
        if (num < M) return numberToWords(num/T) + " Thousand" + (num%T == 0?"":(" "+ numberToWords(num%T)));
        if (num < B) return numberToWords(num/M) + " Million" + (num%M == 0?"":(" "+ numberToWords(num%M)));
        return numberToWords(num/B) + " Billion" + (num%B== 0?"":(" "+ numberToWords(num%B)));
    }
}
