package com.pewee.algorithm.dynamicProgram;

import java.util.Iterator;

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
	
	public static void main(String[] args) {
		//int profit = new SellBuy().maxProfit(2, new int[] {3,2,6,5,0,3});
		int profit = new SellBuy().maxProfit(4, new int[] {2,4,1});
		System.out.println(profit);
	}
	
	public int maxProfit(int k, int[] prices) {
		//在letcode上一次买加一次卖计为1次交易,我这里把一次买和一次卖分别记作一次交易,所以这里要 乘2
		k = 2* k;
		//特殊条件
		if(prices.length == 0 || prices.length == 1 || k <= 1) {
			return 0;
		}
		int ret = 0;
		int[][][] result = new int[prices.length][2][k+1];
		//初始化,为第0天的值赋为最小值,因为第0天的多次交易不可能达到,为第0天买和买股票赋值
		for (int i = 0;i < result[0].length; i++) {
			for (int j = 0;j < result[0][0].length; j++) {
				result[0][i][j] = Integer.MIN_VALUE + 1000;
			}
		}
		result[0][0][0] = 0;
		result[0][1][1] = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			for (int j = 0;j < k+1;j++) {
				if (j == 0) {//不做任何交易
					result[i][0][j] = 0;
					result[i][1][j] = Integer.MIN_VALUE + 1000;
				} else {
					result[i][0][j] = Math.max(result[i -1][0][j] , result[i -1][1][j - 1] + prices[i] );
					result[i][1][j] = Math.max(result[i -1][0][j - 1] - prices[i], result[i -1][1][j] );
				}
				int tmp = result[i][0][j] > result[i][1][j] ? result[i][0][j] : result[i][1][j];
				if(ret < tmp) {
					ret = tmp;	
				}
			}
		}
		return ret;
    }
}
