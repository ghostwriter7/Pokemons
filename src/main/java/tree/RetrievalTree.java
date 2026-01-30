package tree;

/**
 * Retrieval Tree, also known as Trie from Re'trie'val.
 * Great for caching / autocomplete.
 * This example is for autocomplete with English words.
 */
public class RetrievalTree {

    private final Node[] children = new Node[26];
    private static final int INDEX_ZERO = "a".codePointAt(0);

    public RetrievalTree() {
        populate(children);
    }

    public void insert(String word) {
        Node current = children[getIndexOf(word.charAt(0))];

        for (int i = 1; i < word.length(); i++) {
            var idx = getIndexOf(word.charAt(i));
            if (current.children[idx] != null) {
                current = current.children[idx];
            } else {
                var node = new Node(word.charAt(i));
                current.children[idx] = node;
                current = node;
            }
        }

        current.setWord(true);
    }

    private int getIndexOf(char c) {
        return c - INDEX_ZERO;
    }

    private void populate(Node[] nodes) {
        for (int i = INDEX_ZERO; i <= "z".codePointAt(0); i++) {
            nodes[i - INDEX_ZERO] = new Node((char) i);
        }
    }

    private class Node {
        private final Character value;
        private boolean isWord;
        private Node[] children = new Node[26];

        public Node(Character value) {
            this.value = value;
            populate(children);
        }

        public void setWord(boolean word) {
            isWord = word;
        }
    }
}
