package com.pewee.algorithm.hashAndSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * 两数相加
 * https://leetcode-cn.com/problems/two-sum/
 * @author pewee
 * 思路,采用hashmap存值和下标
 */
public class TwoSum {
	
	public static void main(String[] args) {
		int[] twoSum = new TwoSum().twoSum(new int[] {2,7,11,15}, 9);
		for (int i : twoSum) {
			System.out.println(i);
		}
	}
	
	public int[] twoSum(int[] nums, int target) {
		Map<Integer,LinkedList<Integer>> container = new HashMap<Integer,LinkedList<Integer>>();
		for(int i = 0;i < nums.length ; i ++) {
			if(container.containsKey(nums[i])) {
				container.get(nums[i]).add(i);
			} else {
				LinkedList<Integer> linkedList = new LinkedList<Integer>();
				linkedList.add(i);
				container.put(nums[i],linkedList );
			}
		}
		
		for(int i = 0;i< nums.length ; i++) {
			int val = target - nums[i];
			if(null != container.get(val)) {
				Iterator<Integer> iterator = container.get(val).iterator();
				while(iterator.hasNext()) {
					Integer next = iterator.next();
					if(next != i) {
						return new int[] {i,next};
					}
				}
				
			}
		}
		return null;
    }
}
