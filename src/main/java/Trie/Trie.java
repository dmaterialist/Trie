package Trie;

import java.util.*;

public final class Trie {
    private static class Leaf {
        Map<Character, Leaf> children = new HashMap<Character, Leaf>();
        boolean wordend = false;
    }

    Leaf root = new Leaf();

    public void add(String word) {
        Leaf n = root;

        for (char c : word.toCharArray()) {
            if (!n.children.containsKey(c))
                n.children.put(c, new Leaf());
            n = n.children.get(c);
        }
        n.wordend = true;
    }

    public boolean find(String word) {
        if (word.isEmpty()) return false;
        Leaf n = root;
        for (char c : word.toCharArray()) {
            if (!n.children.containsKey(c))
                return false;
            else n = n.children.get(c);
        }
        return n.wordend;
    }

    private Set<String> recursion(Leaf n, Set<String> result, String temp) {
        for (char i : n.children.keySet()) {
            Leaf a = n.children.get(i);
            String t = temp + i;
            if (a.wordend) {
                result.add(t);
                if (!a.children.isEmpty()) {
                    recursion(a, result, t);
                }
            } else {
                recursion(a, result, t);
            }
        }
        return (result);
    }

    public Set<String> prefix(String word) {
        Leaf n = root;
        Set<String> result = new HashSet<String>();
        for (char c : word.toCharArray()) {
            if (!n.children.containsKey(c))
                return Collections.emptySet();
            n = n.children.get(c);
            if (word.indexOf(c) == word.length() - 1) {
                if (n.wordend) result.add(word);
            }
        }
        return recursion(n, result, word);
    }

    public void delete(String word) {
        if (!this.find(word)) return;
        Leaf n = root;
        Leaf a = n;
        if (word.isEmpty()) return;
        char s = word.charAt(0);
        for (char c : word.toCharArray()) {
            if (n.children.containsKey(c)) {
                if (n.children.keySet().size() > 1 || n.wordend) {
                    a = n;
                    s = c;
                }
                n = n.children.get(c);
            }
        }
        if (n.children.keySet().isEmpty())
            a.children.remove(s);
        else n.wordend = false;
    }
}
