package com.pewee.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 单词搜索 II
 * @author pewee
 * https://leetcode-cn.com/problems/word-search-ii/
 *	
 * 思路1:DFS
 * 思路2:trie树,把候选词放到trie树里面
 */
public class WordSearch2 {
	
	public static void main(String[] args) {
		
		String[] words = new String[] {"oath","pea","eat","rain"};
		char[][] board = new char[][] {
			{'o','a','a','n'},
			{'e','t','a','e'},
			{'i','h','k','r'},
			{'i','f','l','v'}
		};
		
		List<String> findWords = new WordSearch2().findWords(board, words);
		for (String string : findWords) {
			System.out.println(string);
		}
	}
	
	public List<String> findWords(char[][] board, String[] words) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (String string : words) {
			if (dfs(board, string, 0,0,0)) {
				arrayList.add(string);
			};
		}
		return arrayList;
    }
	
	/**
	 * 思路1
	 * @param board
	 * @param word
	 * @param visited
	 * @return
	 */
	private boolean dfs(char[][] board, String word,int row,int column,int index){
		//初始化每个单词的已访问格子,同一个单词不能重复访问
		int[][] visited = new int[board.length][board[0].length];
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0;j < visited[i].length; j++) {
				visited[i][j] = 0;
			}
		}
		char[] wordchar = new char[word.length()];
		for (int i=0;i<word.length();i++) {
			wordchar[i]  = word.charAt(i);
		}
		
		for (int i = 0; i< board.length; i++) {
			for (int j= 0;j < board[0].length;j++) {
				if(findNext(board, wordchar, i, j, 0, visited,true)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 在当前格子的上下左右找下一个与index对应的char,只能找新的,不走回头路那必须要用visited来记住从哪来的
	 * @param board
	 * @param wordchar
	 * @param row
	 * @param column
	 * @param index
	 * @param visited
	 * @return
	 */
	private boolean findNext(char[][] board, char[] wordchar,int row,int column,int index,int[][] visited ,boolean start) {
		if(index  == 0 && start) {//第一个元素不用找当前格子的上下左右,可以直接看当前格子再递归下一个
			if(board[row][column] == wordchar[index]) {
				visited[row][column] = 1;
				if(!findNext(board, wordchar, row , column, index + 1, visited,false)) {
					visited[row][column] = 0;
					return false;
				} else {
					return true;
				}
			} else {
				return false;//第一个对不上,返回
			}
		}
		//当index大于单词的长度,说明匹配完毕,可以在顶层返回true,这里返回一直会回溯到顶层,加入list
		if(index > wordchar.length -1) {
			return true;
		}
		//只找上下左右,而且不能回头找
		if(row -1 >= 0) {//往上
			if(visited[row -1][column] == 0) {
				if(board[row -1][column] == wordchar[index]) {
					visited[row -1][column] = 1;
					if(!findNext(board, wordchar, row - 1, column, index + 1, visited,false)) {
						visited[row -1][column] = 0;
					} else {
						return true;
					}
				}
			}
		}
		
		if(row +1 < board.length ) {//往下
			if(visited[row + 1][column] == 0) {
				if(board[row + 1][column] == wordchar[index]) {
					visited[row + 1][column] = 1;
					if(!findNext(board, wordchar, row + 1, column, index + 1, visited,false)) {
						visited[row + 1][column] = 0;
					} else {
						return true;
					}
				}
			}
		}
		
		if(column - 1 >= 0 ) {//往左
			if(visited[row][column - 1] == 0) {
				if(board[row][column - 1] == wordchar[index]) {
					visited[row][column - 1] = 1;
					if(!findNext(board, wordchar, row, column - 1, index + 1, visited,false)) {
						visited[row][column - 1] = 0;
					} else {
						return true;
					}
				}
			}
		}
		if(column + 1 < board[0].length ) {//往右
			if(visited[row][column + 1] == 0) {
				if(board[row][column + 1] == wordchar[index]) {
					visited[row][column + 1] = 1;
					if(!findNext(board, wordchar, row, column + 1, index + 1, visited,false)) {
						visited[row][column + 1] = 0;
					} else {
						return true;
					}
				}
			}
		}
		return false;//第四个走完都不行说明上下左右都不匹配,说明这个格子不行,要换格子
	}
}
