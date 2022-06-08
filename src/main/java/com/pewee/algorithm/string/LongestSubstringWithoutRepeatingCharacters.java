package com.pewee.algorithm.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 无重复字符的最长子串
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 输入: s = "abcabcbb"
   输出: 3 
   解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * @author pewee
 * 
 */
public class LongestSubstringWithoutRepeatingCharacters {
	public static void main(String[] args) {
		int substring = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abba");
		System.out.println(substring);
	}
	
	/**
	 * 思路2:滑动窗口 用start,end来指示未遇到重复的字符,直接指向原串,这样省去了解法1中维护队列的开销
	 * 执行用时：4 ms, 在所有 Java 提交中击败了86.39%的用户
	 * 41.8 MB, 在所有 Java 提交中击败了14.00%的用户
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
		int max = 0;
		if (s.length() == 0) {
			return 0;
		}
		int start = 0;
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		for (int i = 0; i < s.length() ; i++) {//i表示end
			char charAt = s.charAt(i);
			if (map.containsKey(charAt) && map.get(charAt) >= start) {//由于map没有clear,需要排除掉窗口以外之前已经扫过的重复字符,否则start窗口会往回拨
				start = map.get(charAt) + 1;
			} 
			max = Math.max(max, i - start + 1);
			map.put(charAt, i);
		}
		return max; 
    }
	
	/**
	 * 思路1:维护一个队列 如果进来重复的字符 就从队列头pop 一直pop到第一个该重复元素出去为止 然后把新的点加进来 重新计算长度
	 * 执行用时：11 ms  在所有 Java 提交中击败了17.75%的用户
	 * 内存消耗：42.2 MB, 在所有 Java 提交中击败了5.07%的用户
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring1(String s) {
		int max = 0;
		if (s.length() == 0) {
			return 0;
		}
		HashSet<Character> set = new HashSet<Character>();
		ArrayBlockingQueue<Character> queue = new ArrayBlockingQueue<Character>(s.length());
		for (int i = 0; i < s.length() ; i++) {
			char charAt = s.charAt(i);
			if (set.contains(charAt)) {
				Character c = null;
				do {
					c = queue.poll();
					set.remove(c);
				} while (!c.equals(charAt));
			} 
			set.add(charAt);
			queue.add(charAt);
			max = Math.max(max, queue.size());
		}
		return max;
    }
	
	
}
