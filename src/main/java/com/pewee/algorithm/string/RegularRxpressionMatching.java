package com.pewee.algorithm.string;
/**
 * 正则表达式匹配
 * 字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * https://leetcode.cn/problems/regular-expression-matching/
 * @author pewee
 * 
 * 
 * 
 * 
 */
public class RegularRxpressionMatching {
	public boolean isMatch(String s, String p) {
		
		
		
		
		/**
		int idx1 = 0;//主串索引
		for (int idx2 = 0;idx2< p.length(); ) {
			char pChar = s.charAt(idx2);
			if (pChar == '.') {
				idx1++;
				idx2++;
			}
			else if (pChar == '*') {
				idx2++;
				idx1 = 
			} 
			else {
				if (s.charAt(idx1) == pChar) {
					idx1++;
					idx2++;
				} else {
					return false;
				}
			}
		}
		**/
		return true;
    }
}
