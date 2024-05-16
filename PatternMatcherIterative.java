/**
 * This class is used for pattern matching while the pattern just has dots(.).
 * In this case, the length of the patten and the word will be same
 * The match function is designed in iteratively
 */
public class PatternMatcherIterative {
    public String str1;
    public String pattern;

    public boolean match(String str1, String pattern) {
        this.str1 = str1;
        this.pattern = pattern;
        if(str1.length() != pattern.length()) {
            return false;
        }
        int count = 0;
        for(int i = 0; i < str1.length(); i++) {
            if(str1.charAt(i) == pattern.charAt(i) || pattern.charAt(i) == '.') {
                count++;
            }
            else if(str1.charAt(i) != pattern.charAt(i) && pattern.charAt(i) != '.') {
                return false;
            }
        }
        return count == str1.length();
    }

}
