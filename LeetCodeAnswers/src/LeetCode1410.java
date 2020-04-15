/*author@ Qian Cai
 * Title@ HTML Entity Parser
 * HTML entity parser is the parser that takes HTML code as input and 
 * replace all the entities of the special characters by the characters 
 * itself.

The special characters and their entities for HTML are:

Quotation Mark: the entity is &quot; and symbol character is ".
Single Quote Mark: the entity is &apos; and symbol character is '.
Ampersand: the entity is &amp; and symbol character is &.
Greater Than Sign: the entity is &gt; and symbol character is >.
Less Than Sign: the entity is &lt; and symbol character is <.
Slash: the entity is &frasl; and symbol character is /.
Given the input text string to the HTML parser, you have to implement the entity parser.

Return the text after replacing the entities by the special characters.
 * 
 */
public class LeetCode1410 {
	public String entityParser(String text) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        int n = text.length();
        while (i < n){
            if (text.charAt(i) != '&'){
                sb.append(text.charAt(i++));
            } else {
                if (i+6 <= n && text.substring(i, i+6).equals("&quot;")){
                    sb.append('\"');
                    i = i+6;
                } else if (i+6 <= n && text.substring(i, i+6).equals("&apos;")){
                    sb.append("\'");
                    i = i+6;
                } else if (i+5 <= n && text.substring(i, i+5).equals("&amp;")){
                    sb.append("&");
                    i = i+5;
                } else if (i+4 <= n && text.substring(i, i+4).equals("&gt;")){
                    sb.append(">");
                    i = i+4;
                } else if (i+4 <= n && text.substring(i, i+4).equals("&lt;")){
                    sb.append("<");
                    i = i+4;
                } else if (i+7 <= n && text.substring(i, i+7).equals("&frasl;")){
                    sb.append("/");
                    i = i+7;
                } else {
                    sb.append(text.charAt(i++));
                }
            }
        }
        return sb.toString();
    }
}
