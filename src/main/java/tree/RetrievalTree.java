package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Retrieval Tree, also known as Trie from Re'trie'val.
 * Great for caching / autocomplete.
 * This example is for autocomplete with English words.
 */
public class RetrievalTree {

    private final TrieNode root = new TrieNode();
    private static final int INDEX_ZERO = "a".codePointAt(0);

    public void insert(String word) {
        assertNotEmpty(word);

        TrieNode current = root;

        for (var character : word.toCharArray()) {
            var index = getIndexOf(character);

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        assertNotEmpty(word);
        var endNode = getEndNode(word);
        return endNode != null && endNode.isEndOfWord;
    }

    public boolean isPrefix(String prefix) {
        assertNotEmpty(prefix);
        var endNode = getEndNode(prefix);
        return endNode != null;
    }

    public boolean isEmpty() {
        return isEmpty(root);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();

        List<String> path = new ArrayList<>();
        walk(root, path, sb);

        return sb.toString().trim();
    }

    private boolean isEmpty(TrieNode node) {
        for (var child : node.children) {
            if (child != null) return false;
        }
        return true;
    }

    private TrieNode getEndNode(String word) {
        TrieNode current = root;

        for (var character : word.toCharArray()) {
            var index = getIndexOf(character);

            if (current.children[index] == null) return null;

            current = current.children[index];
        }

        return current;
    }

    private void walk(TrieNode node, List<String> path, StringBuilder sb) {
        if (node == null) return;

        if (node.isEndOfWord) sb.append(String.join("", path)).append(" ");

        for (var i = 0; i < node.children.length; i++) {
            path.add(Character.toString((char) (i + INDEX_ZERO)));
            walk(node.children[i], path, sb);
            path.removeLast();
        }
    }

    private int getIndexOf(char c) {
        return c - INDEX_ZERO;
    }

    private void assertNotEmpty(String word) {
        if (word == null || word.isEmpty()) throw new IllegalArgumentException("Word cannot be empty");
    }

    private class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private boolean isEndOfWord;
    }
}
