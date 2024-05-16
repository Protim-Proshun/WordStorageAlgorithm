import java.io.*;
/**
 * This is the main function or Driver class where the file has been read and putting the words into the HashTable
 * After getting the at most 3 final matched words with pattern, longest common subsequence is done and printed into
 * output.txt
 */
public class regex_matcher_40185075 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        PrintWriter pr = null;

        String outputFileName = "output.txt";
        String s1 = "/Users/protim/Documents/Study/Concordia/Winter2023/PPS/Problem solving/AlgorithmProject/src/input";
        br = new BufferedReader(new FileReader(s1));
        pr = new PrintWriter(new FileOutputStream(outputFileName));

        String x = br.readLine();
        int dictionarySize = Integer.parseInt(x);
        String pattern = null;
        MyHashTableBinary dictionary = new MyHashTableBinary();

        for (int i = 0; i < dictionarySize; i++) {
            x = br.readLine();
            dictionary.addWord(x);
        }
        pattern = br.readLine();

        //dictionary.showWords();

        //System.out.println(pattern);

        String[] trimmedMatch = new String[3];

        BinarySearchTree bst = new BinarySearchTree();
        bst.setPattern(pattern);

        System.out.println("Words in Alphabetic order: ");
        for (int p = 0; p < 26; p++) {
            if (dictionary.array[p].count > 0) {
                TreeNode temp = dictionary.array[p].root;
                bst.inorder(temp);
            }
        }

        System.out.println("Matched words:");
        trimmedMatch = bst.printTrimmedMatch();

        System.out.println("Longest common subsequence: ");

        if (trimmedMatch[2] != null) {
            String firstLcs = longestCommonSubsequence(trimmedMatch[0], trimmedMatch[1]);
            String secondLcs = longestCommonSubsequence(firstLcs, trimmedMatch[2]);
            System.out.println(secondLcs);
            pr.println(secondLcs);
        }
        else if(trimmedMatch[1] == null) {
            System.out.println(trimmedMatch[0]);
            pr.println(trimmedMatch[0]);
        }
        else if(trimmedMatch[0] == null) {
            System.out.println("No match");
        }
        else {
            System.out.println(longestCommonSubsequence(trimmedMatch[0], trimmedMatch[1]));
            pr.println(longestCommonSubsequence(trimmedMatch[0], trimmedMatch[1]));
        }

        br.close();
        pr.close();


    }

    public static String longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = m;
        int j = n;
        StringBuilder lcs = new StringBuilder();

        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.insert(0, text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.toString();
    }
}