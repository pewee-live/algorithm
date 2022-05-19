package com.pewee.algorithm.string;

/**
 * 在主串中找到模式串的下标
 * @author pewee
 * 1.构造next数组,数组的大小为模式串p.length() -1 ,因为数组大小为p.length没有意义,说明主串和模式串完全匹配
 * 在计算next数组设置2个指针 i ,j
 * i就是我遍历模式串的小标,表示我现在遍历的模式串p的一个前缀子串,
 * j只有在p[i] = p[j]时自增.j的值就是next[i]数组的值
 * 当p[i]!=p[j],若next[i-1] > 0 说明在p[0,i-1]有首尾匹配的子串,我们忽略掉尾子串,把j往前拨,newj= next[j-1]把问题转换为p[i]和p[next[j-1]]的问题,相当于忽略了后面那段重复的串
 * 如果往前拨了还是不满足p[newj] = p[i],那就把j再往前拨,直到0 
 * 若next[i-1] = 0,那说明前p[0,i-1]没有首位匹配的子串,j直接拨到0
 * 
 * 以String p = "GTGTGCF"为例;
 * 那next数组大小为6,next[0-5]
 * i = 0 ,j = 0  G   因为一个字符 没有前后缀子串;next[0] = j = 0;
 * i = 1,j = 0  GT   p[i]!= p[j],G!=T;next[1] = j = 0;
 * i = 2,j = 0  GTG  p[i] = p[j],G=G;j往后拨一位,next[2] = j = 1;
 * i = 3,j = 1  GTGT p[i] = p[j],T=T;j往后拨一位,next[3] = j = 2;
 * i = 4,j = 2  GTGTG p[i] = p[j],G=G;j往后拨一位,next[4] = j = 3;
 * i = 5,j = 3  GTGTGC p[i] != p[j],C!=T;j往回拨,j = next[j - 1] = next[2] = 1;
 * i = 5,j = 1  GTG{忽略重复串}C p[i]!= p[]j ,C!=T;j再往回,j = next[j - 1] = next[1] = 0
 * i = 5,j = 0  G{忽略重复串}C  p[i]!= p[j],C!=G;j已经为0,没法往前了 next[5] = j = 0
 */
public class Kmp {
	
	public static void main(String[] args) {
		int[] next = new Kmp().getNext("GTGTGCF");
		System.out.println(next);
	}
	
	public  int[] getNext (String p) {
		int length = p.length();
		int[]  next = new int[length -1];
		int j = 0;
		for (int i = 1; i < length -1; i++) {
			while(j > 0 && p.charAt(i) != p.charAt(j)) {
				j = next[j -1];
			}
			if (p.charAt(i) == p.charAt(j)) {
				j++;
			}
			next[i] = j;
		}
		return next;
	}
	
	public int kmp(String p,String txt) {
		int[] next = getNext(p);
		int j = 0;
		for (int i = 0; i < txt.length(); i++) {
			while (j>0 && txt.charAt(i) != p.charAt(j)) {
				j = next[j - 1];
			}
			if(txt.charAt(i) != p.charAt(j)) {
				j++;
			}
			if (j == p.length()) {
				return i - j + 1;
			}
		}
		return -1;
	}
	
	
	
	
	
	
}
