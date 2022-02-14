package com.pewee.algorithm.dynamicProgram;
/**
 * 买卖股票问题(一直股票最多只能持有1股)
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 * @author pewee
 * 思路:暴力递归
 * 思路: 动态规划
 * 第i天买了股票 f(i) = f(i - 1) + (-a[i])
 * 第i天卖了股票 f(i) = f(i - 1) + (a[i])
 * 买了后不能再买,卖了后不能再卖,一维数组无法表示股票的买卖状态
 * 添加一个维度
 * f(i,j) 其中j = 0 或 1 代表持有或不持有股票
 * f(i,0) = max( f(i -1,0) , f(i-1,1) + a[i] )
 * f(i,1) = max( f(i -1,0) - a[i] , f(i-1,1))
 * 这无法表示交易次数
 * f(i,j,k) k的取值0-k,表示已经交易了多少次
 * 
 * f(i,0,k) = max( f(i -1,0 ,k ) , f(i-1,1,k -1) + a[i] )
 * f(i,1,k) = max( f(i -1,0,k-1) - a[i] , f(i-1,1))
 */
public class SellBuy {
	public int maxProfit(int k, int[] prices) {
		//特殊条件
		if(prices.length == 1 || k <= 1) {
			return 0;
		}
		int[][][] result = new int[prices.length][2][k];
		result[0][0][0] = 0;
		result[0][1][1] = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			 
			
		}
		
		return 0;
    }
}
