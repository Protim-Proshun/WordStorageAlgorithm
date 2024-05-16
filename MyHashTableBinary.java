/**
 * This class is used to store words those are coming from the input.txt file
 * This HashTable is having the array of size 26 as the number of total alphabets
 * And each index of the array is Binary Search Tree
 */
public class MyHashTableBinary {

    int size = 26;
    BinarySearchTree[] array = new BinarySearchTree[26];
    int wordValue = 0;

    public MyHashTableBinary() {
        for(int i = 0; i < 26; i++) {
            array[i] = new BinarySearchTree();
        }
    }

    public int polynomial(String word) {

        String upperCaseWord = word;
        int i = upperCaseWord.charAt(0);
        for(int j = 0; j < upperCaseWord.length(); j++) {
            wordValue = wordValue + upperCaseWord.charAt(j);
        }
        return i - 65;
    }

    public void addWord(String word) {
        int indexNumber = polynomial(word.toUpperCase());
        array[indexNumber].add(word.toUpperCase(), wordValue);
        wordValue = 0;
    }

    public void showWords() {
        for(int i = 0; i < size; i++) {
            TreeNode root = array[i].root;
            array[i].inorder(root);
            System.out.println(array[i].count);
        }
    }

}
