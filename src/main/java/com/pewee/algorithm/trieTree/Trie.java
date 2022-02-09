package com.pewee.algorithm.trieTree;
/**
 * 实现一个trie树
 * @author pewee
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 *
 */
public class Trie {
	
	private TrieNode root;
	
    public Trie() {
    	root = new TrieNode(' ');
    }
    
    public void insert(String word) {
    	TrieNode now = root;
    	for (int i = 0; i < word.length() ; i++) {
    		char charAt = word.charAt(i);
    		if (now.child[charAt - 'a'] == null) {
    			now.child[charAt - 'a'] = new TrieNode(charAt);
    		}
    		now = now.child[charAt - 'a'];
    	}
    	now.isEnd = true;
    }
    
    public boolean search(String word) {
    	TrieNode now = root;
    	for (int i = 0; i < word.length() ; i++) {
    		char charAt = word.charAt(i);
    		if (now.child[charAt - 'a'] == null) {
    			return false;
    		}
    		now = now.child[charAt - 'a'];
    	}
    	return now.isEnd;
    }
    
    public boolean startsWith(String prefix) {
    	TrieNode now = root;
    	for (int i = 0; i < prefix.length() ; i++) {
    		char charAt = prefix.charAt(i);
    		if (now.child[charAt - 'a'] == null) {
    			return false;
    		}
    		now = now.child[charAt - 'a'];
    	}
    	return true;
    }
}

class TrieNode {
	
	public char c ;
	
	public boolean isEnd;
	
	public TrieNode[] child ;
	
	TrieNode(char c){
		this.c = c;
		child = new TrieNode[26];//假设只有26个字母
	}
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
