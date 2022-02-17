package com.pewee.algorithm.dynamicProgram;
/**
 * 最长递增子序列
 * @author pewee
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * 思路1:暴力回溯,每个数字可以选or不选,类似于求最大乘积(该解法会超时)
 * 思路2:f(i),i为选上第i个元素时的从头到i最长递增子序列的长度
 * 当nums[i] > nums[j](j取0->i-1),f(i) = max{f(0->j)} + 1  前面的最大子序列+1
 */
public class LongestIncreasingSubsequence {
	
	public static void main(String[] args) {
		
		new LongestIncreasingSubsequence().lengthOfLIS(new int[] {10,9,2,5,3,7,101,18});
		
	}
	
	
	public int lengthOfLIS(int[] nums) {
		
		int[] result = new int[nums.length];
		
		for (int i = 0 ;i < nums.length ; i++) {
			for (int j = 0; j < i;j++) {
				if (nums[i] > nums[j]) {//i之前的序列有比他小的序列
					if (result[i] < result[j] + 1) {
						result[i] = result[j] + 1;
					}
				} 
			}
			//遍历完i之前的所有元素发现还是没比他小的子序列,那就从i开始计算
			if(result[i] == 0) {
				result[i] = 1;
			}
		}
		
		int max = 0;
		for (int i =0; i < result.length ; i++) {
			if (max < result[i]) {
				max = result[i];
			}
		}
		return max;
    }
}
