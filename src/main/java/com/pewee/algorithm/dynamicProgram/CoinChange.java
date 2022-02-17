package com.pewee.algorithm.dynamicProgram;
/**
 * 零钱兑换
 * @author pewee
 * https://leetcode-cn.com/problems/coin-change/
 * 思路:类似于爬楼梯,coins为一次爬的个数,amount为楼梯数,求最少的步数,定义一个数组存每个楼梯能到达需要的最少步数
 */
public class CoinChange {
	
	public static void main(String[] args) {
		int change = new CoinChange().coinChange(new int[] {1,2,5}, 11);
		System.out.println(change);
	}
	
	public int coinChange(int[] coins, int amount) {
		int[] result = new int[amount + 1];
		for (int i = 1; i < result.length ; i++) {
			int step = -1;//默认某一步不可达
			for (int j = 0 ; j < coins.length ; j++) {
				if (i - coins[j] >= 0 && result[i - coins[j]] >= 0) {//金额从某步可以达到,且这一步是可达的
					if (step == -1 || step > result[i - coins[j]] + 1) {//找出最小的可达步数
						step = result[i - coins[j]] + 1;
					}
				}
			}
			result[i] = step;
		}
		return result[amount];
    }
}
