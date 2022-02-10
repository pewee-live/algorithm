package com.pewee.algorithm.dynamicProgram;
/**
 * 爬楼梯
 * @author pewee
 *	https://leetcode-cn.com/problems/climbing-stairs/
 * 思路:每一层由他的下一层或2层爬上来的.故f(n) = f(n -1 ) + f(n - 2)
 *
 */
public class ClimbingStairs {
	public int climbStairs(int n) {
		if(n == 1) {
			return 1;
		}
		int[] container = new int[n];
		container[0] = 1;
		container[1] = 2;
		if(n > 2) {
			for (int i =2;i < n;i++) {
				container[i] = container[i - 1] + container[i - 2];
			}
		}
		return container[n-1];
    }
}
