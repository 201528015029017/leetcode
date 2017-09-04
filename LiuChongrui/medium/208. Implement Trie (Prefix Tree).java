/*
思路：字典树的简单实现，每个节点用一个26长度数组来存储它的（a-z）孩子节点，用isEnd标志是否有单词以此节点结尾
 */


class TrieNode {
    // Initialize your data structure here.
    TrieNode son[];
    boolean isEnd;
    int num;
    char charactor;
    public TrieNode() {
        son = new TrieNode[26];
        num = 1;
        isEnd = false;
        charactor = ' ';
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++){
            int c_index = word.charAt(i) - 'a';
            if (p.son[c_index] != null){
                p = p.son[c_index];
                p.num++;
            }
            else{
                p.son[c_index] = new TrieNode();
                p = p.son[c_index];
            }
        }
        p.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++){
            int c_index = word.charAt(i)-'a';
            if (p.son[c_index] != null){
                p = p.son[c_index];
            }
            else return  false;
        }
        return p.isEnd;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++){
            int c_index = prefix.charAt(i)-'a';
            if (p.son[c_index] != null){
                p = p.son[c_index];
            }
            else return  false;
        }
        return (p.num > 0);
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");