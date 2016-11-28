import java.util.Iterator;
import java.util.Map;

/**
 * Created by Oscar on 16-11-25.
 */
public class Trie implements Iterator<Map.Entry<String, Integer>> {
    private Node root;

    public Trie() {
        root = new Node();
    }

    public void put(String word) {
        Node wordNode = root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int index = letter - 'a';
            if(wordNode.LetterArr[index] == null) {
                Node temp = new Node();
                wordNode.LetterArr[index] = temp;
                wordNode = temp;
            } else {
                wordNode = wordNode.LetterArr[index];
            }
        }
        wordNode.counter += 1;
    }

    private Node search(String text) {
        Node wordNode = root;
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            int index = letter - 'a';
            if (wordNode.LetterArr[index] != null) {
                wordNode = wordNode.LetterArr[index];
            } else {
                return null;
            }
        }
        return wordNode;
    }

    public int get(String text) {
        Node temp = search(text);
        if (temp == null)
            return 0;
        return temp.counter;
    }

    public int count(String text) {
        Node head = search(text);
        if(head == null)
            return 0;
        return searchChild(head);
    }

    private int searchChild(Node head) {
        int counter = 0;
        if(head == null)
            return 0;
        for(int i = 0; i < 26; i++) {
            if(head.LetterArr[i] != null){
                counter += searchChild(head.LetterArr[i]);
            }
        }
        return counter += head.counter;
    }

    public int countDistinct(String text) {
        Node head = search(text);
        if(head == null)
            return 0;
        return searchDistinctChild(head);
    }

    private int searchDistinctChild(Node firstHead) {
        int counter = 0;
        if(firstHead == null)
            return 0;
        for(int i = 0; i < 26; i++) {
            if(firstHead.LetterArr[i] != null)
                counter += searchDistinctChild(firstHead.LetterArr[i]);
        }
        if(firstHead.counter > 0)
          counter += 1;
        return counter;
    }

    public Iterator<Map.Entry<String, Integer>> iterator() {
        return new TrieIterator(root);
    }

    private class TrieIterator implements Iterator<Map.Entry<String, Integer>> {
        Node root;
        Node current;

        public TrieIterator(Node root) {
            this.root = this.current = root;
        }

        public Map.Entry next() {

            return Map< >;
        }

        public boolean hasNext() {
            if(searchDistinctChild(current) > 0)
                return true;

            return false;
        }
    }

    private class Node {
        Node[] LetterArr;
        int counter = 0;
        Node() {
            this.LetterArr = new Node[26];
        }
    }
}
