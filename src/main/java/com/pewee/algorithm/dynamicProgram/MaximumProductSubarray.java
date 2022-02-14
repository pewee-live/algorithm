package com.pewee.algorithm.dynamicProgram;
/**
 * 乘积最大子数组
 * @author pewee
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 * 
 * 思路:暴力递归回溯,每个数都乘或不乘,不乘把max清0,最后返回最大的
 * 思路:动态规划: f(i) = min(f(i - 1)) * arr[i] //arr[i]为负数 或 max(f(i - 1)) * arr[i] // arr[i]为正数 当arr[i]=0,那需要将f(i) = 0
 * 存储状态应定义二位数组,第一维为index,第二维包含在每个index的时候正最大和负的最大值
 *
 */
public class MaximumProductSubarray {
	
	public static void main(String[] args) {
		int[] arr = new int[]{0,-1,4,-4,5,-2,-1,-1,-2,-3,0,-3,0,1,-1,-4,4,6,2,3,0,-5,2,1,-4,-2,-1,3,-4,-6,0,2,2,-1,-5,1,1,5,-6,2,1,-3,-6,-6,-3,4,0,-2,0,2};
		System.out.println(new MaximumProductSubarray().maxProduct0(arr));
	}
	
	public int maxProduct(int[] nums) {
		if(nums.length == 1) {
			return nums[0];
		}
		int[][] result = new int[nums.length][2];
		result[0][0] = nums[0];
		result[0][1] = nums[0];
		for (int i = 1;i< nums.length ; i++) {
			if(nums[i] >= 0) {
				result[i][0] = Math.max( (result[i - 1][0] * nums[i]), nums[i]);
				result[i][1] = Math.min( (result[i - 1][1] * nums[i]), nums[i]);
			} else {
				result[i][0] = Math.max( (result[i - 1][1] * nums[i]), nums[i]);
				result[i][1] = Math.min( (result[i - 1][0] * nums[i]), nums[i]);
			}
		}
		
		int res = 0;
		for (int i = 0;i< result.length ; i++) {
			if(res < result[i][0]) {
				res = result[i][0];
			}
		}
		return res;
    }
	
	/**
	 * 以下暴力方法时间复杂度不通过
	 * @param nums
	 * @return
	 */
	public int maxProduct0(int[] nums) {
		if(nums.length == 1) {
			return nums[0];
		}
		recurision(nums, 0, 0);
		return globalmax;
    }
	
	private int globalmax;
	
	/**
	 * 暴力递归,此解法时间复杂度不通过
	 * @param nums
	 * @param index 当前要处理的值
	 * @param max 目前最大值
	 * @return
	 */
	@Deprecated
	private int recurision(int[] nums ,int index, int max) {
		if(index == nums.length) {
			return max;
		}
		
		//乘之前的值
		int t1 = recurision(nums, index + 1, max * nums[index]);
		//不乘之前的值,直接以他为新起点重新计算
		int t2 = recurision(nums, index + 1,  nums[index]);
		int t3 = t1 > t2 ? t1 : t2;
		int thisMax = t3 > max ? t3 : max;
		if(thisMax > globalmax) {
			globalmax = thisMax;
		}
		return  t3 > max ? t3 : max ;
	}
}
