package trie;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void findTest() {
        Trie test = new Trie();
        String[] t = new String[]{"Yuno", "Yuki", "Yunost", "Yura", "Yuri", "Yurassic", "Yurassa", "Yurasso", ""};
        for (int i = 0; i < 9; i++) {
            test.add(t[i]);
        }
        for (int i = 0; i < 8; i++) {
            assertTrue(test.find(t[i]));
        }
    }

    @Test
    public void falseFindTest() {
        Trie test = new Trie();
        String[] t = new String[]{"Yuno", "Yuki", "Yunost", "Yura", "Yuri", "Yurassic", "Yurassa", "Yurasso", ""};
        for (int i = 0; i < 9; i++) {
            test.add(t[i]);
        }
        assertFalse(test.find(""));
        assertFalse(test.find("Meguca"));
        assertFalse(test.find("Yu"));
        assertFalse(test.find("Yunos"));
        assertFalse(test.find("Yurass"));
    }

    @Test
    public void prefixTest() {
        Trie test = new Trie();
        String[] t = new String[]{"Yuno", "Yuki", "Yunost", "Yura", "Yuri", "Yurassic", "Yurassa", "Yurasso", ""};
        for (int i = 0; i < 9; i++) {
            test.add(t[i]);
        }
        assertEquals(Set.of("Yuri", "Yura", "Yurassa", "Yurasso", "Yurassic"), test.prefix("Yur"));
        assertEquals(Set.of("Yuno", "Yuki", "Yunost", "Yura", "Yuri", "Yurassic", "Yurassa", "Yurasso"), test.prefix("Yu"));
        assertEquals(Set.of("Yura", "Yurassic", "Yurassa", "Yurasso"), test.prefix("Yura"));
        assertEquals(Set.of("Yunost", "Yuno"), test.prefix("Yun"));
        assertEquals(Set.of(), test.prefix("meguka"));
    }

    @Test
    public void deleteFindTest() {
        Trie test = new Trie();
        String[] t = new String[]{"Yuno", "Yuki", "Yunost", "Yura", "Yuri", "Yurassic", "Yurassa", "Yurasso", ""};
        for (int i = 0; i < 9; i++) {
            test.add(t[i]);
        }
        for (int i = 0; i < 9; i++) {
            test.delete(t[i]);
            assertFalse(test.find(t[i]));
            test.add(t[i]);
        }
    }

    @Test
    public void deletePrefixTest() {
        Trie test = new Trie();
        String[] t = new String[]{"Yuno", "Yuki", "Yunost", "Yura", "Yuri", "Yurassic", "Yurassa", "Yurasso", ""};
        for (int i = 0; i < 9; i++) {
            test.add(t[i]);
        }
        test.delete("Yuri");
        assertEquals(Set.of("Yura", "Yurassa", "Yurasso", "Yurassic"), test.prefix("Yur"));
        test.delete("Yurassic");
        assertEquals(Set.of("Yura", "Yurassa", "Yurasso"), test.prefix("Yur"));
        test.delete("Yuno");
        assertEquals(Set.of("Yunost"), test.prefix("Yun"));
        test.add("Yuno");
        test.delete("Yunos");
        assertEquals(Set.of("Yunost", "Yuno"), test.prefix("Yun"));
    }
}