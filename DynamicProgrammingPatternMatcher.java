/**
 * This class is used for pattern matching while there are one or multiple stars(*) in the pattern
 * the match function is designed by following dynamic programming
 */
public class DynamicProgrammingPatternMatcher {

    public boolean match(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();

        boolean[][] dp = new boolean[textLength + 1][patternLength + 1];
        dp[0][0] = true;

        for (int j = 1; j <= patternLength; j++) {
            if (pattern.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= textLength; i++) {
            for (int j = 1; j <= patternLength; j++) {
                if (text.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        return dp[textLength][patternLength];
    }
}
