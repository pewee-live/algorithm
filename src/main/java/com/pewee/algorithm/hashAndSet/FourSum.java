package com.pewee.algorithm.hashAndSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 四数之和
 * https://leetcode-cn.com/problems/3sum/
 * 
 * @author pewee
 * 思路:遍历循环2层,第3层循环用双指针
 */
public class FourSum {
	
	public static void main(String[] args) {
		List<List<Integer>> four = new FourSum().fourSum(new int[] {1,-2,-5,-4,-3,3,3,5
				}, -11);
		for (List<Integer> list : four) {
			for (Integer i : list) {
				System.out.print(i);
			}
			System.out.println(";");
			
		}
	}
	
	
	public List<List<Integer>> fourSum(int[] nums, int target) {
		//先排序,后面的元素会始终比他大
		Arrays.sort(nums);
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		for(int i = 0; i < nums.length - 3; i ++) {
			for(int x = i + 1; x < nums.length - 2; x++) {
				for(int j = x + 1, k = nums.length -1; j < k;) {
					int val = nums[i] + nums[x] + nums[j] + nums[k];
					if(val > target ) {
						k--;
					} else if(val < target) {
						j++;
					} else {
						ArrayList<Integer> arrayList = new ArrayList<Integer>();
						arrayList.add(nums[i]);
						arrayList.add(nums[x]);
						arrayList.add(nums[j]);
						arrayList.add(nums[k]);
						result.add(arrayList);
						j++;
						k--;
					}
				}
			}
		}
		ArrayList arrayList = new  ArrayList();
		arrayList.addAll(result);
		return arrayList;		
    }
}
