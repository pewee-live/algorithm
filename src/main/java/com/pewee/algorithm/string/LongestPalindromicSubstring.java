package com.pewee.algorithm.string;

import java.util.HashMap;

/**
 * 最长回文子串
 * 回文的意思是正着念和倒着念一样，如：上海自来水来自海上
 * https://leetcode.cn/problems/longest-palindromic-substring/
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * @author pewee
 * 
 * 思路1:以每个字符为中心字符往外扩散,3个指针l,r,pivot ,在一次循环中pivot不动,l,r分别向左右变.每次扩散比较记录max回文长度,如果大于max那就更新最长子串
 * 思路2:
 *
 */
public class LongestPalindromicSubstring {
	public static void main(String[] args) {
		String palindrome = new LongestPalindromicSubstring().longestPalindrome("tattarrattat");
		System.out.println(palindrome);
	}
	
	
	public String longestPalindrome(String s) {
		int max = 0;
		String ret = "";
		for (int i = 0;i < s.length() ; i++) {
			int l = i;
			int r = i;
			int len = 1;
			//往外扩散
			//不能往2边同时扩散要考虑能否往一边扩散且还满足需求如'cfaart'这种,以及"cfaaaart"这种,这种往一遍扩散后还能再往2边扩散如"abba"这种
			//由于pivot是从左到右的,这种情况只用考虑向右扩张.
			//往2边扩散后,之后的所有扩散必须要往2边扩散.也就是说只有第一次扩散可以只往右边扩散.当l左移后就只能往2边都移
			while ( ( l - 1 >= 0 && r + 1 < s.length() && s.charAt(l - 1) == s.charAt(r + 1) )  || ( l == i &&  r + 1 < s.length() && s.charAt(r + 1) == s.charAt(i))) {
				if (l - 1 >= 0 && r + 1 < s.length() && s.charAt(l - 1) == s.charAt(r + 1) ) {
					l = l -1;
					r = r + 1;
				} else {
					r = r + 1;
				}
				len = r - l + 1;
				max = Math.max(max, len);
				if (max == len ) {
					ret = s.substring(l ,r + 1);
				}
			}
		}
		if (0 == max) {
			ret = "" +  s.charAt(0);
		}
		return ret;
    }
	
}
