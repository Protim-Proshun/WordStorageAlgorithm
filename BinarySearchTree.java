/**
 * The TreeNode class is for storing the word or string those are coming from input.txt
 * Each word of the file is a node. Each node having the word, the ASCII value of the word and left, right node reference
 * <p>
 * The BinarySearchTree class is used to design the Binary search Tree on the basis of the ASCII value of the word
 * This class has a significant value for its inorder function that helps to inorder traversal of the tree
 * while finding the words ascending order they are going to match with the pattern and if matches, will insert into
 * the string type array trimmedMatch having size 3
 */

class TreeNode {
    public String word;
    public int wordValue;
    public TreeNode left;
    public TreeNode right;


    public TreeNode(String word, int wordValue, TreeNode left, TreeNode right) {
        this.word = word;
        this.wordValue = wordValue;
        this.left = left;
        this.right = right;
    }
}

public class BinarySearchTree {

    public TreeNode root;
    int count;
    String currentWord;
    static public String pattern;
    static public String[] trimmedMatch = new String[3];
    PatternMatcherIterative pmI = new PatternMatcherIterative();
    static int containsStar = 0;
    static int trimmedMatchIndex = 0;
    DynamicProgrammingPatternMatcher pm = new DynamicProgrammingPatternMatcher();

    public BinarySearchTree() {
        root = null;
    }

    public void add(String word, int wordValue) {
        TreeNode newNode = new TreeNode(word, wordValue, null, null);
        count++;
        if(root == null) {
            root = newNode;
        }
        else {
            TreeNode temp = root;
            while(true) {
                if(newNode.wordValue < temp.wordValue) {
                    if(temp.left == null) {
                        temp.left = newNode;
                        break;
                    }
                    temp = temp.left;
                }
                else {
                    if(temp.right == null) {
                        temp.right = newNode;
                        break;
                    }
                    temp = temp.right;
                }
            }
        }
    }

    public void inorder(TreeNode root) {
        if(root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.word + " ");
        //currentWord = root.word.toLowerCase();
        if(containsStar == 0) {
            if(root.word.length() == pattern.length()) {
                if(pmI.match(root.word, pattern)) {
                    trimmedMatch[trimmedMatchIndex] = root.word;
                    trimmedMatchIndex++;
                    if(trimmedMatchIndex == 3) {
                        return;
                    }
                }
            }
        }
        else {
            if(pm.match(root.word, pattern)) {
                trimmedMatch[trimmedMatchIndex] = root.word;
                trimmedMatchIndex++;
                if(trimmedMatchIndex == 3) {
                    return;
                }
            }
        }
        inorder(root.right);
    }

    public void setPattern(String pattern) {
        this.pattern = pattern.toUpperCase();
        findStar();
    }

    public void findStar() {
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '*') {
                containsStar = 1;
                break;
            }
        }
    }

    public String[] printTrimmedMatch() {
        for(int i = 0; i < trimmedMatch.length; i++) {
            System.out.println(trimmedMatch[i]);
        }
        return trimmedMatch;
    }

}

